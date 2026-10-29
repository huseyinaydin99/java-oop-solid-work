package tr.com.huseyinaydin.loganalyzer.parsing;

import tr.com.huseyinaydin.loganalyzer.domain.LogEntry;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat.FIELD_COUNT;
import static tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat.FIELD_SEPARATOR_REGEX;
import static tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat.TIMESTAMP_FORMATTER;

/**
 * "zaman | SEVİYE | Servis | Mesaj" biçimindeki satırları ayrıştırır.
 */
public final class PipeDelimitedLogLineParser implements LogLineParser {

    private static final int TIMESTAMP_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int SERVICE_INDEX = 2;
    private static final int MESSAGE_INDEX = 3;

    @Override
    public Optional<LogEntry> parse(String rawLine) {
        if (rawLine == null || rawLine.isBlank()) {
            return Optional.empty();
        }
        // Limit verilmezse mesajın içindeki "|" karakteri de bölünür ve mesaj kaybolur.
        String[] fields = rawLine.split(FIELD_SEPARATOR_REGEX, FIELD_COUNT);
        if (fields.length != FIELD_COUNT) {
            return Optional.empty();
        }
        return toEntry(fields);
    }

    private Optional<LogEntry> toEntry(String[] fields) {
        Optional<LocalDateTime> timestamp = parseTimestamp(fields[TIMESTAMP_INDEX]);
        Optional<LogLevel> level = LogLevel.fromText(fields[LEVEL_INDEX]);
        String service = fields[SERVICE_INDEX].strip();
        String message = fields[MESSAGE_INDEX].strip();

        if (timestamp.isEmpty() || level.isEmpty() || service.isEmpty() || message.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new LogEntry(timestamp.get(), level.get(), service, message));
    }

    private Optional<LocalDateTime> parseTimestamp(String text) {
        try {
            return Optional.of(LocalDateTime.parse(text.strip(), TIMESTAMP_FORMATTER));
        } catch (DateTimeParseException e) {
            // Geçersiz tarih bir format hatasıdır; satır "hatalı" sayılır ve raporda görünür, kaybolmaz.
            return Optional.empty();
        }
    }
}
