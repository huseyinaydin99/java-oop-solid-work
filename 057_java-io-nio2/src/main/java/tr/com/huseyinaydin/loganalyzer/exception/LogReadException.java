package tr.com.huseyinaydin.loganalyzer.exception;

import java.nio.file.Path;

public class LogReadException extends LogToolException {

    public LogReadException(Path file, Throwable cause) {
        super("Log dosyası okunamadı: " + file.toAbsolutePath(), cause);
    }

    public LogReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
