package tr.com.huseyinaydin.loganalyzer.report;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;

import java.util.List;

/**
 * Analiz sonucunu belirli bir formatta satırlara çevirir.
 * Yeni bir format (JSON, HTML...) eklemek için yalnızca bu arayüzü uygulayan yeni bir sınıf yazmak yeter.
 */
public interface ReportFormatter {

    String fileExtension();

    List<String> format(AnalysisResult result);
}
