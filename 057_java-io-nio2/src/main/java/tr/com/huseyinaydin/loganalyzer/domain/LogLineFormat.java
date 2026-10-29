package tr.com.huseyinaydin.loganalyzer.domain;

import java.time.format.DateTimeFormatter;

/**
 * Log satırı formatına dair tüm sabitler.
 * Örnek: "2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı"
 */
public final class LogLineFormat {

    public static final String FIELD_SEPARATOR = " | ";
    public static final String FIELD_SEPARATOR_REGEX = "\\|";
    public static final int FIELD_COUNT = 4;
    public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LogLineFormat() {
    }

    /**
     * Bir kaydı dosyaya yazılacak satır biçimine çevirir; ayrıştırıcının tersidir.
     */
    public static String format(LogEntry entry) {
        return String.join(FIELD_SEPARATOR,
                entry.timestamp().format(TIMESTAMP_FORMATTER),
                entry.level().name(),
                entry.service(),
                entry.message());
    }
}
