package tr.com.huseyinaydin.loganalyzer.exception;

/**
 * Aracın tüm hatalarının ortak atası; çağıran taraf isterse tek tipte yakalayabilsin.
 */
public class LogToolException extends RuntimeException {

    public LogToolException(String message) {
        super(message);
    }

    public LogToolException(String message, Throwable cause) {
        super(message, cause);
    }
}
