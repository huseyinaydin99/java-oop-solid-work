package tr.com.huseyinaydin.support;

import java.io.IOException;

public class WorkspaceException extends RuntimeException {

    public WorkspaceException(String message, IOException cause) {
        super(message, cause);
    }
}
