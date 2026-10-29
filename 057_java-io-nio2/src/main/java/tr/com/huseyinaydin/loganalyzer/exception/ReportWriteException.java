package tr.com.huseyinaydin.loganalyzer.exception;

import java.io.IOException;
import java.nio.file.Path;

public class ReportWriteException extends LogToolException {

    public ReportWriteException(Path target, IOException cause) {
        super("Rapor yazılamadı: " + target.toAbsolutePath(), cause);
    }
}
