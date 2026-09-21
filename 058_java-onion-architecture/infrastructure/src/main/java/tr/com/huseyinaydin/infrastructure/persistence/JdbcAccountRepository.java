package tr.com.huseyinaydin.infrastructure.persistence;

import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.domain.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class JdbcAccountRepository implements AccountRepository {
    private final Connection connection;

    public JdbcAccountRepository(Connection connection) {
        this.connection = connection;
        initSchema();
    }

    private void initSchema() {
        try (PreparedStatement stmt = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS accounts (id VARCHAR(36) PRIMARY KEY, balance DECIMAL)")) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> findById(UUID id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT balance FROM accounts WHERE id = ?")) {
            stmt.setString(1, id.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Account(id, rs.getBigDecimal("balance")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Account account) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "MERGE INTO accounts (id, balance) KEY(id) VALUES (?, ?)")) {
            stmt.setString(1, account.getId().toString());
            stmt.setBigDecimal(2, account.getBalance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
