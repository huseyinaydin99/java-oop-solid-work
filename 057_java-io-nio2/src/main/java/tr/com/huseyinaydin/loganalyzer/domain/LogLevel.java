package tr.com.huseyinaydin.loganalyzer.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 * Log satırında karşılaşılabilecek seviyeler.
 */
public enum LogLevel {
    INFO,
    WARN,
    ERROR;

    /**
     * Metni seviyeye çevirir; tanınmayan değer için boş döner.
     */
    public static Optional<LogLevel> fromText(String text) {
        if (text == null) {
            return Optional.empty();
        }
        // Locale.ROOT şart: Türkçe yerel ayarda "info".toUpperCase() "İNFO" olur ve eşleşme bozulur.
        String normalized = text.strip().toUpperCase(Locale.ROOT);
        return Arrays.stream(values())
                .filter(level -> level.name().equals(normalized))
                .findFirst();
    }
}
