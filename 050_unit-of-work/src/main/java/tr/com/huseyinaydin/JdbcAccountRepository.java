package tr.com.huseyinaydin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAccountRepository implements AccountRepository {
    private final Connection connection;

    // Bağlantıyı Unit of Work'ten alıyoruz ki aynı transaction içinde çalışsın
    public JdbcAccountRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account findById(String id) {
        String sql = "SELECT id, balance FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("id"), rs.getDouble("balance"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Veritabanından hesap okunurken hata oluştu: " + id, e);
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Hesap güncellenirken hata oluştu: " + account.getId(), e);
        }
    }
}