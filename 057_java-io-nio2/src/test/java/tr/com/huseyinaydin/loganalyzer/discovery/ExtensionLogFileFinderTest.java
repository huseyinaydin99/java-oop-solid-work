package tr.com.huseyinaydin.loganalyzer.discovery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tr.com.huseyinaydin.loganalyzer.exception.LogDirectoryNotFoundException;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtensionLogFileFinderTest {

    @TempDir
    Path tempDir;

    @Test
    void throwsMeaningfulExceptionWhenDirectoryDoesNotExist() {
        LogFileFinder finder = new ExtensionLogFileFinder(".log");
        Path missing = tempDir.resolve("olmayan-klasor");

        LogDirectoryNotFoundException exception =
                assertThrows(LogDirectoryNotFoundException.class, () -> finder.find(missing));

        assertTrue(exception.getMessage().contains("olmayan-klasor"));
    }
}
