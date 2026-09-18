package tr.com.huseyinaydin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Gerçek veritabanı bağlantısını kuran somut sınıfımız
public class SimpleConnectionProvider implements ConnectionProvider {
    private final String url = "jdbc:mysql://localhost:3306/bank_db"; // Kendi DB url'iniz
    private final String user = "root";
    private final String password = "toor";

    @Override
    public Connection getConnection() throws SQLException {
        // DriverManager üzerinden gerçek ve yeni bir bağlantı açıyoruz
        return DriverManager.getConnection(url, user, password);
    }
}