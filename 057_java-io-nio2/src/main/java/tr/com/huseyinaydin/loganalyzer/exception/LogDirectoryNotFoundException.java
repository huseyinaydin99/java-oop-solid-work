package tr.com.huseyinaydin.loganalyzer.exception;

import java.nio.file.Path;

public class LogDirectoryNotFoundException extends LogToolException {

    public LogDirectoryNotFoundException(Path directory) {
        super("Log klasörü bulunamadı: " + directory.toAbsolutePath());
    }
}
