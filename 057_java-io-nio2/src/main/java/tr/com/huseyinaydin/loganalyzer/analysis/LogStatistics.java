package tr.com.huseyinaydin.loganalyzer.analysis;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.domain.ErrorSummary;
import tr.com.huseyinaydin.loganalyzer.domain.LogEntry;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Tek bir analiz çalışması boyunca sayaçları tutan biriktirici.
 * Satırlar tek tek geldiği için tüm kayıtları listede saklamaya gerek kalmaz.
 */
final class LogStatistics {

    private static final long ONE = 1L;

    private final Map<LogLevel, Long> levelCounts = new EnumMap<>(LogLevel.class);
    private final Map<String, Long> errorCountsByService = new HashMap<>();
    private long malformedLineCount;
    private LocalDateTime firstErrorAt;
    private LocalDateTime lastErrorAt;

    void record(LogEntry entry) {
        levelCounts.merge(entry.level(), ONE, Long::sum);
        if (entry.level() == LogLevel.ERROR) {
            recordError(entry);
        }
    }

    void recordMalformed() {
        malformedLineCount++;
    }

    AnalysisResult toResult() {
        return new AnalysisResult(levelCounts, malformedLineCount, buildErrorSummary());
    }

    private void recordError(LogEntry entry) {
        errorCountsByService.merge(entry.service(), ONE, Long::sum);
        // Dosyalar kronolojik sırayla gelmeyebilir; bu yüzden "ilk gelen" değil "en erken" seçilir.
        LocalDateTime time = entry.timestamp();
        if (firstErrorAt == null || time.isBefore(firstErrorAt)) {
            firstErrorAt = time;
        }
        if (lastErrorAt == null || time.isAfter(lastErrorAt)) {
            lastErrorAt = time;
        }
    }

    private Optional<ErrorSummary> buildErrorSummary() {
        // Eşitlikte alfabetik ilk servis seçilir; aynı girdi her çalıştırmada aynı raporu üretsin.
        Comparator<Map.Entry<String, Long>> byCountThenName = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey(Comparator.reverseOrder()));

        return errorCountsByService.entrySet().stream()
                .max(byCountThenName)
                .map(top -> new ErrorSummary(top.getKey(), top.getValue(), firstErrorAt, lastErrorAt));
    }
}
