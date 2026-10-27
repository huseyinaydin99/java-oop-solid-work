package tr.com.huseyinaydin.shared.persistence;

/** Benzersizlik kuralı ihlal edildiğinde fırlatılır (Oracle'da ORA-00001). */
public class DuplicateKeyException extends DataAccessException {

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
