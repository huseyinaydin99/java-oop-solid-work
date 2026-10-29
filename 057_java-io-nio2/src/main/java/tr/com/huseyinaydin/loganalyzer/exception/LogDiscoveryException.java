package tr.com.huseyinaydin.loganalyzer.exception;

import java.io.IOException;
import java.nio.file.Path;

public class LogDiscoveryException extends LogToolException {

    public LogDiscoveryException(Path directory, IOException cause) {
        super("Log klasörü listelenemedi: " + directory.toAbsolutePath(), cause);
    }
}
