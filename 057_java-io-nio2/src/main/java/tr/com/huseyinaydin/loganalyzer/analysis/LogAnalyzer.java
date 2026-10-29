package tr.com.huseyinaydin.loganalyzer.analysis;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;

import java.util.stream.Stream;

/**
 * Ham satır akışını analiz eder. Satırların nereden geldiğini (dosya, ağ, test) bilmez.
 */
public interface LogAnalyzer {

    AnalysisResult analyze(Stream<String> rawLines);
}
