package tr.com.huseyinaydin.demo;

import tr.com.huseyinaydin.loganalyzer.pipeline.LogAnalysisPipeline;
import tr.com.huseyinaydin.loganalyzer.pipeline.PipelineSummary;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Asıl senaryo: örnek logları üretir, pipeline'ı çalıştırır, raporları ve son klasör yapısını gösterir.
 */
public final class LogAnalysisDemo implements DemoStep {

    private static final String LOG_FOLDER = "logs";
    private static final String REPORT_FOLDER = "reports";
    private static final String INDENT_UNIT = "  ";

    private final SampleLogFactory sampleLogFactory;
    private final LogAnalysisPipeline pipeline;
    private final Charset charset;
    private final PrintStream out;

    public LogAnalysisDemo(SampleLogFactory sampleLogFactory, LogAnalysisPipeline pipeline,
                           Charset charset, PrintStream out) {
        this.sampleLogFactory = Objects.requireNonNull(sampleLogFactory, "sampleLogFactory");
        this.pipeline = Objects.requireNonNull(pipeline, "pipeline");
        this.charset = Objects.requireNonNull(charset, "charset");
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Log Analiz Aracı: bulma → okuma → ayrıştırma → analiz → rapor → arşiv";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        Path logDirectory = workDirectory.resolve(LOG_FOLDER);
        sampleLogFactory.createSampleLogs(logDirectory);
        out.println("Örnek loglar oluşturuldu: <çalışma>/" + workDirectory.relativize(logDirectory));

        PipelineSummary summary = pipeline.run(logDirectory, workDirectory.resolve(REPORT_FOLDER));

        for (Path report : summary.reports()) {
            printFile(report);
        }
        printTree(workDirectory, logDirectory);
        printTree(workDirectory, workDirectory.resolve(REPORT_FOLDER));
    }

    private void printFile(Path file) throws IOException {
        out.println();
        out.println("--- " + file.getFileName() + " ---");
        Files.readAllLines(file, charset).forEach(out::println);
    }

    private void printTree(Path workDirectory, Path root) throws IOException {
        out.println();
        out.println("Klasör yapısı (Files.walk): <çalışma>/" + workDirectory.relativize(root));
        // walk da list gibi açık dizin tanıtıcıları tutar; kapatılması gerekir.
        try (Stream<Path> paths = Files.walk(root)) {
            paths.sorted()
                    .filter(path -> !path.equals(root))
                    .forEach(path -> out.println(describe(root, path)));
        }
    }

    private String describe(Path root, Path path) {
        Path relative = root.relativize(path);
        String indent = INDENT_UNIT.repeat(relative.getNameCount());
        String suffix = Files.isDirectory(path) ? "/" : "";
        return indent + relative.getFileName() + suffix;
    }
}
