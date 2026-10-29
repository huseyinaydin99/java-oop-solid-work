package tr.com.huseyinaydin.loganalyzer.pipeline;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;

import java.nio.file.Path;
import java.util.List;

/**
 * Akıştaki adımları dışarıya haber verir. Konsol, log ya da arayüz bu arayüzü uygular;
 * böylece pipeline System.out'a bağımlı olmaz.
 */
public interface PipelineListener {

    default void onLogFilesFound(List<Path> logFiles) {
    }

    default void onAnalysisCompleted(AnalysisResult result) {
    }

    default void onReportWritten(Path report) {
    }

    default void onFileArchived(Path source, Path target) {
    }

    static PipelineListener silent() {
        return new PipelineListener() {
        };
    }
}
