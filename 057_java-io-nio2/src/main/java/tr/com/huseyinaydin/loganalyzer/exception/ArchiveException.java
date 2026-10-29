package tr.com.huseyinaydin.loganalyzer.exception;

import java.io.IOException;
import java.nio.file.Path;

public class ArchiveException extends LogToolException {

    public ArchiveException(Path source, Path target, IOException cause) {
        super("Log dosyası arşivlenemedi: " + source.toAbsolutePath() + " -> " + target.toAbsolutePath(), cause);
    }
}
