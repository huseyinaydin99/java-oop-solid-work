package tr.com.huseyinaydin;

public interface UnitOfWork extends AutoCloseable {
    AccountRepository getAccountRepository();
    void commit() throws Exception;
    void rollback() throws Exception;
}