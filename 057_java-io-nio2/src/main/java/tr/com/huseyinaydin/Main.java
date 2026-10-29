package tr.com.huseyinaydin;

import tr.com.huseyinaydin.demo.ClassicVsNioReadDemo;
import tr.com.huseyinaydin.demo.ConsolePipelineListener;
import tr.com.huseyinaydin.demo.DemoStep;
import tr.com.huseyinaydin.demo.EncodingDemo;
import tr.com.huseyinaydin.demo.FileBasicsDemo;
import tr.com.huseyinaydin.demo.LargeFileStreamingDemo;
import tr.com.huseyinaydin.demo.LogAnalysisDemo;
import tr.com.huseyinaydin.demo.OpenOptionsDemo;
import tr.com.huseyinaydin.demo.PathBasicsDemo;
import tr.com.huseyinaydin.demo.SampleLogFactory;
import tr.com.huseyinaydin.demo.TryWithResourcesDemo;
import tr.com.huseyinaydin.loganalyzer.analysis.StatisticsLogAnalyzer;
import tr.com.huseyinaydin.loganalyzer.archive.TimestampedLogArchiver;
import tr.com.huseyinaydin.loganalyzer.discovery.ExtensionLogFileFinder;
import tr.com.huseyinaydin.loganalyzer.exception.LogToolException;
import tr.com.huseyinaydin.loganalyzer.parsing.LogLineParser;
import tr.com.huseyinaydin.loganalyzer.parsing.PipeDelimitedLogLineParser;
import tr.com.huseyinaydin.loganalyzer.pipeline.LogAnalysisPipeline;
import tr.com.huseyinaydin.loganalyzer.reading.StreamingLogFileReader;
import tr.com.huseyinaydin.loganalyzer.report.CsvReportFormatter;
import tr.com.huseyinaydin.loganalyzer.report.FileReportWriter;
import tr.com.huseyinaydin.loganalyzer.report.TextReportFormatter;
import tr.com.huseyinaydin.support.TemporaryWorkspace;
import tr.com.huseyinaydin.support.WorkspaceException;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Clock;
import java.util.List;

/**
 * Uygulamanın giriş noktası ve bileşenlerin birbirine bağlandığı tek yer (composition root).
 * "new" anahtar kelimesinin toplandığı yer burası; diğer sınıflar bağımlılıklarını hazır alır.
 */
public final class Main {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String WORKSPACE_PREFIX = "log-analiz-";
    private static final String LOG_EXTENSION = ".log";
    private static final String REPORT_BASE_NAME = "log-report";
    private static final String ARCHIVE_FOLDER = "archive";
    private static final String STEP_HEADER = "%n==== %d) %s ====%n";
    private static final int FAILURE_EXIT_CODE = 1;

    private Main() {
    }

    public static void main(String[] args) {
        PrintStream out = System.out;
        try {
            runDemo(out);
        } catch (IOException | LogToolException | WorkspaceException e) {
            System.err.println("Demo başarısız oldu: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(FAILURE_EXIT_CODE);
        }
    }

    private static void runDemo(PrintStream out) throws IOException {
        // Klasör, demo hata ile bitse bile try-with-resources sayesinde silinir.
        try (TemporaryWorkspace workspace = TemporaryWorkspace.create(WORKSPACE_PREFIX)) {
            out.println("Geçici çalışma klasörü: " + workspace.root());
            List<DemoStep> steps = buildSteps(out);
            for (int index = 0; index < steps.size(); index++) {
                DemoStep step = steps.get(index);
                out.printf(STEP_HEADER, index + 1, step.title());
                step.run(workspace.root());
            }
            out.println();
            out.println("Demo bitti, geçici klasör siliniyor...");
            // Sonucu ekranda göstermek için erken kapatıyoruz; close tekrar çağrılınca sorun çıkarmaz.
            workspace.close();
            out.println("Geçici klasör hâlâ var mı? " + Files.exists(workspace.root()));
        }
    }

    private static List<DemoStep> buildSteps(PrintStream out) {
        LogLineParser parser = new PipeDelimitedLogLineParser();
        return List.of(
                new PathBasicsDemo(out),
                new FileBasicsDemo(out),
                new ClassicVsNioReadDemo(out),
                new EncodingDemo(out),
                new OpenOptionsDemo(out),
                new TryWithResourcesDemo(out),
                new LargeFileStreamingDemo(parser, out),
                new LogAnalysisDemo(new SampleLogFactory(CHARSET), buildPipeline(parser, out), CHARSET, out));
    }

    private static LogAnalysisPipeline buildPipeline(LogLineParser parser, PrintStream out) {
        return new LogAnalysisPipeline(
                new ExtensionLogFileFinder(LOG_EXTENSION),
                new StreamingLogFileReader(CHARSET),
                new StatisticsLogAnalyzer(parser),
                List.of(
                        new FileReportWriter(new TextReportFormatter(), REPORT_BASE_NAME, CHARSET),
                        new FileReportWriter(new CsvReportFormatter(), REPORT_BASE_NAME, CHARSET)),
                new TimestampedLogArchiver(ARCHIVE_FOLDER, Clock.systemDefaultZone()),
                new ConsolePipelineListener(out));
    }
}
