package tr.com.huseyinaydin.application.port.out;

public interface TransactionManager {
    void begin();
    void commit();
    void rollback();
}
