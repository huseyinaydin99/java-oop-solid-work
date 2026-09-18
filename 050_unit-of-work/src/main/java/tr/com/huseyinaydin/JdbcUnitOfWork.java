package tr.com.huseyinaydin;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUnitOfWork implements UnitOfWork {
    private final Connection connection;
    private AccountRepository accountRepository;

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false); // Kontrolü veritabanından alıyoruz
    }

    @Override
    public AccountRepository getAccountRepository() {
        if (accountRepository == null) {
            // Repozitory'ye aynı connection nesnesini veriyoruz
            accountRepository = new JdbcAccountRepository(connection);
        }
        return accountRepository;
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}