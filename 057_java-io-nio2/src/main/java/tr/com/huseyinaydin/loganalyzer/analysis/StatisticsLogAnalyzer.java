package tr.com.huseyinaydin.loganalyzer.analysis;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.parsing.LogLineParser;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Satırları ayrıştırıcıya verip sonuçları istatistik olarak biriktirir.
 */
public final class StatisticsLogAnalyzer implements LogAnalyzer {

    private final LogLineParser parser;

    public StatisticsLogAnalyzer(LogLineParser parser) {
        this.parser = Objects.requireNonNull(parser, "parser");
    }

    @Override
    public AnalysisResult analyze(Stream<String> rawLines) {
        // Her çağrıda yeni biriktirici: analizci durumsuz kalır, aynı nesne güvenle tekrar kullanılır.
        LogStatistics statistics = new LogStatistics();
        // Boş satırlar log kaydı değil, dosya sonu ya da ayraç; hatalı satır sayılmaz.
        rawLines.filter(Predicate.not(String::isBlank))
                .forEach(line -> parser.parse(line)
                        .ifPresentOrElse(statistics::record, statistics::recordMalformed));
        return statistics.toResult();
    }
}
