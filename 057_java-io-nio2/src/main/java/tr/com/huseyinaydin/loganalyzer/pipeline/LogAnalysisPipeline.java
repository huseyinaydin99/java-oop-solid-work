package tr.com.huseyinaydin.loganalyzer.pipeline;

import tr.com.huseyinaydin.loganalyzer.analysis.LogAnalyzer;
import tr.com.huseyinaydin.loganalyzer.archive.LogArchiver;
import tr.com.huseyinaydin.loganalyzer.discovery.LogFileFinder;
import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.exception.LogReadException;
import tr.com.huseyinaydin.loganalyzer.reading.LogFileReader;
import tr.com.huseyinaydin.loganalyzer.report.ReportWriter;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Bulma → okuma → ayrıştırma → analiz → rapor → arşiv akışını yönetir.
 * Hiçbir adımı kendisi yapmaz; yalnızca sıralar.
 */
public final class LogAnalysisPipeline {

    private final LogFileFinder finder;
    private final LogFileReader reader;
    private final LogAnalyzer analyzer;
    private final List<ReportWriter> reportWriters;
    private final LogArchiver archiver;
    private final PipelineListener listener;

    public LogAnalysisPipeline(LogFileFinder finder,
                               LogFileReader reader,
                               LogAnalyzer analyzer,
                               List<ReportWriter> reportWriters,
                               LogArchiver archiver,
                               PipelineListener listener) {
        this.finder = Objects.requireNonNull(finder, "finder");
        this.reader = Objects.requireNonNull(reader, "reader");
        this.analyzer = Objects.requireNonNull(analyzer, "analyzer");
        this.reportWriters = List.copyOf(reportWriters);
        this.archiver = Objects.requireNonNull(archiver, "archiver");
        this.listener = Objects.requireNonNull(listener, "listener");
    }

    public PipelineSummary run(Path logDirectory, Path reportDirectory) {
        List<Path> logFiles = finder.find(logDirectory);
        listener.onLogFilesFound(logFiles);

        AnalysisResult result = analyze(logFiles);
        listener.onAnalysisCompleted(result);

        List<Path> reports = writeReports(result, reportDirectory);
        // Arşiv en sona bırakıldı: rapor yazılamazsa loglar yerinde kalır ve tekrar denenebilir.
        List<Path> archived = archive(logFiles);
        return new PipelineSummary(logFiles, result, reports, archived);
    }

    private AnalysisResult analyze(List<Path> logFiles) {
        // flatMap, her dosyanın stream'ini içeriği tüketilince kendisi kapatır.
        // Dıştaki try-with-resources ise analiz yarıda kesilirse açık kalan son dosyayı da kapatır.
        try (Stream<String> allLines = logFiles.stream().flatMap(reader::lines)) {
            return analyzer.analyze(allLines);
        } catch (UncheckedIOException e) {
            // Files.lines okuma sırasındaki hatayı (ör. bozuk kodlama) unchecked olarak fırlatır.
            throw new LogReadException("Log satırları okunurken hata oluştu", e);
        }
    }

    private List<Path> writeReports(AnalysisResult result, Path reportDirectory) {
        List<Path> reports = new ArrayList<>();
        for (ReportWriter writer : reportWriters) {
            Path report = writer.write(result, reportDirectory);
            listener.onReportWritten(report);
            reports.add(report);
        }
        return reports;
    }

    private List<Path> archive(List<Path> logFiles) {
        List<Path> archived = new ArrayList<>();
        for (Path file : logFiles) {
            Path target = archiver.archive(file);
            listener.onFileArchived(file, target);
            archived.add(target);
        }
        return archived;
    }
}
