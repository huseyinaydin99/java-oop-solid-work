package tr.com.huseyinaydin.loganalyzer.parsing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tr.com.huseyinaydin.loganalyzer.domain.LogEntry;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PipeDelimitedLogLineParserTest {

    private final LogLineParser parser = new PipeDelimitedLogLineParser();

    @Test
    void parsesValidLineIncludingTurkishCharactersAndPipeInMessage() {
        Optional<LogEntry> entry = parser.parse(
                "2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı | tekrar denenecek");

        assertTrue(entry.isPresent());
        assertEquals(LocalDateTime.of(2026, 10, 27, 14, 32, 10), entry.get().timestamp());
        assertEquals(LogLevel.ERROR, entry.get().level());
        assertEquals("PaymentService", entry.get().service());
        assertEquals("Ödeme alınamadı | tekrar denenecek", entry.get().message());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "bozuk satır",
            "2026-10-27 14:32:10 | ERROR | PaymentService",
            "2026-13-45 99:99:99 | ERROR | PaymentService | Geçersiz tarih",
            "2026-10-27 14:32:10 | FATAL | PaymentService | Tanımsız seviye",
            "2026-10-27 14:32:10 | ERROR |  | Servis adı boş",
            "   "
    })
    void returnsEmptyForMalformedLine(String malformedLine) {
        assertTrue(parser.parse(malformedLine).isEmpty());
    }
}
