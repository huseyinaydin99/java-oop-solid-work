package tr.com.huseyinaydin.loganalyzer.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Başarıyla ayrıştırılmış tek bir log satırı.
 */
public record LogEntry(LocalDateTime timestamp, LogLevel level, String service, String message) {

    public LogEntry {
        Objects.requireNonNull(timestamp, "timestamp");
        Objects.requireNonNull(level, "level");
        requireText(service, "service");
        requireText(message, "message");
    }

    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " boş olamaz");
        }
    }
}
