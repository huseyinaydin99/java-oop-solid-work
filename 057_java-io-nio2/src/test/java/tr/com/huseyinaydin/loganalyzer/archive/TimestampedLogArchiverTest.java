package tr.com.huseyinaydin.loganalyzer.archive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TimestampedLogArchiverTest {

    @TempDir
    Path tempDir;

    @Test
    void movesFileIntoArchiveFolderWithTimestampedName() throws IOException {
        Clock fixedClock = Clock.fixed(Instant.parse("2026-10-27T14:32:10Z"), ZoneOffset.UTC);
        LogArchiver archiver = new TimestampedLogArchiver("archive", fixedClock);
        Path logFile = tempDir.resolve("payment-service.log");
        List<String> content = List.of("2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı");
        Files.write(logFile, content, StandardCharsets.UTF_8);

        Path archived = archiver.archive(logFile);

        assertEquals(tempDir.resolve("archive").resolve("payment-service_20261027-143210.log"), archived);
        assertFalse(Files.exists(logFile));
        assertEquals(content, Files.readAllLines(archived, StandardCharsets.UTF_8));
    }
}
