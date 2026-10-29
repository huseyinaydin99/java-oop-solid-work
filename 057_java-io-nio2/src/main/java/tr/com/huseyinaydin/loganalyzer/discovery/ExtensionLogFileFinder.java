package tr.com.huseyinaydin.loganalyzer.discovery;

import tr.com.huseyinaydin.loganalyzer.exception.LogDirectoryNotFoundException;
import tr.com.huseyinaydin.loganalyzer.exception.LogDiscoveryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Klasörün yalnızca ilk seviyesindeki, belirli uzantıya sahip dosyaları bulur.
 */
public final class ExtensionLogFileFinder implements LogFileFinder {

    private final String extension;

    public ExtensionLogFileFinder(String extension) {
        this.extension = Objects.requireNonNull(extension, "extension").toLowerCase(Locale.ROOT);
    }

    @Override
    public List<Path> find(Path directory) {
        if (!Files.exists(directory)) {
            throw new LogDirectoryNotFoundException(directory);
        }
        // Files.list bir dizin tanıtıcısı açar; kapatılmazsa işletim sisteminde açık kalır.
        // walk yerine list seçildi: alt klasördeki "archive" içeriği tekrar işlenmesin.
        try (Stream<Path> entries = Files.list(directory)) {
            return entries
                    .filter(Files::isRegularFile)
                    .filter(this::hasExpectedExtension)
                    .sorted()
                    .toList();
        } catch (IOException e) {
            throw new LogDiscoveryException(directory, e);
        }
    }

    private boolean hasExpectedExtension(Path file) {
        return file.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(extension);
    }
}
