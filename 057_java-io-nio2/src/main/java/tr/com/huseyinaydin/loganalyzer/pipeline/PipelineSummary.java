package tr.com.huseyinaydin.loganalyzer.pipeline;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;

import java.nio.file.Path;
import java.util.List;

/**
 * Bir çalıştırmanın sonunda ne olduğunun özeti.
 */
public record PipelineSummary(List<Path> processedFiles,
                              AnalysisResult result,
                              List<Path> reports,
                              List<Path> archivedFiles) {

    public PipelineSummary {
        processedFiles = List.copyOf(processedFiles);
        reports = List.copyOf(reports);
        archivedFiles = List.copyOf(archivedFiles);
    }
}
