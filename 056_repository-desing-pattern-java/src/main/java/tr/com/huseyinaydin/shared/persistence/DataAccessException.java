package tr.com.huseyinaydin.shared.persistence;

/** Veri erişimindeki tüm hataların teknolojiden bağımsız ortak atası. */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
