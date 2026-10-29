package tr.com.huseyinaydin.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackedResourceTest {

    @TempDir
    Path tempDir;

    @Test
    void closesResourcesInReverseOrderOfOpening() throws IOException {
        // Olaylar gerçek bir dosyaya yazılır: kapanış sırası dosyada kalıcı iz olarak doğrulanır.
        Path eventLog = tempDir.resolve("events.log");
        TrackedResource first;
        TrackedResource second;

        try (TrackedResource connection = TrackedResource.open("bağlantı", event -> append(eventLog, event));
             TrackedResource writer = TrackedResource.open("yazıcı", event -> append(eventLog, event))) {
            first = connection;
            second = writer;
        }

        assertTrue(first.isClosed());
        assertTrue(second.isClosed());
        assertEquals(List.of(
                "açıldı: bağlantı",
                "açıldı: yazıcı",
                "kapandı: yazıcı",
                "kapandı: bağlantı"), Files.readAllLines(eventLog, StandardCharsets.UTF_8));
    }

    private static void append(Path file, String event) {
        try {
            Files.writeString(file, event + System.lineSeparator(), StandardCharsets.UTF_8, CREATE, APPEND);
        } catch (IOException e) {
            // Consumer checked exception fırlatamaz; testin sessizce geçmemesi için unchecked'e sarılır.
            throw new UncheckedIOException(e);
        }
    }
}
