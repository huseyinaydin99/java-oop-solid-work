package tr.com.huseyinaydin.loganalyzer.report;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;

import java.nio.file.Path;

/**
 * Analiz sonucunu kalıcı bir rapora dönüştürür ve oluşan dosyanın yolunu döner.
 */
public interface ReportWriter {

    Path write(AnalysisResult result, Path outputDirectory);
}
