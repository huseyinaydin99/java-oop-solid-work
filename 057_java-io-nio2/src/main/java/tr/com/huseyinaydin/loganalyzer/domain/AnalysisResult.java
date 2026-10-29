package tr.com.huseyinaydin.loganalyzer.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Analizin değişmez sonucu. Rapor yazıcılar yalnızca bu nesneyi tanır.
 */
public record AnalysisResult(Map<LogLevel, Long> levelCounts,
                             long malformedLineCount,
                             Optional<ErrorSummary> errorSummary) {

    private static final long ZERO = 0L;

    public AnalysisResult {
        Objects.requireNonNull(levelCounts, "levelCounts");
        Objects.requireNonNull(errorSummary, "errorSummary");
        levelCounts = completeAndFreeze(levelCounts);
    }

    public long countOf(LogLevel level) {
        return levelCounts.get(level);
    }

    public long parsedLineCount() {
        return levelCounts.values().stream().mapToLong(Long::longValue).sum();
    }

    // Raporlarda her seviye sıfır da olsa görünsün ve dışarıdan değiştirilemesin diye.
    private static Map<LogLevel, Long> completeAndFreeze(Map<LogLevel, Long> source) {
        Map<LogLevel, Long> copy = new EnumMap<>(LogLevel.class);
        for (LogLevel level : LogLevel.values()) {
            copy.put(level, source.getOrDefault(level, ZERO));
        }
        return Collections.unmodifiableMap(copy);
    }
}
