package tr.com.huseyinaydin.demo;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.pipeline.PipelineListener;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Pipeline adımlarını konsola sade biçimde yazar.
 */
public final class ConsolePipelineListener implements PipelineListener {

    private final PrintStream out;

    public ConsolePipelineListener(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public void onLogFilesFound(List<Path> logFiles) {
        out.println("[1/4] Bulunan log dosyaları: " + logFiles.stream().map(Path::getFileName).toList());
    }

    @Override
    public void onAnalysisCompleted(AnalysisResult result) {
        out.println("[2/4] Analiz bitti: " + result.parsedLineCount() + " geçerli, "
                + result.malformedLineCount() + " hatalı satır");
    }

    @Override
    public void onReportWritten(Path report) {
        out.println("[3/4] Rapor yazıldı: " + report.getFileName());
    }

    @Override
    public void onFileArchived(Path source, Path target) {
        out.println("[4/4] Arşivlendi: " + source.getFileName() + " -> "
                + source.getParent().relativize(target));
    }
}
