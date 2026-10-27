package tr.com.huseyinaydin.shared.infrastructure.jdbc;

import tr.com.huseyinaydin.shared.persistence.DataAccessException;
import tr.com.huseyinaydin.shared.persistence.DuplicateKeyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Bağlantı, statement, kaynak kapatma ve hata çevirisi gibi tekrar eden JDBC törenini tek yerde toplar. */
public final class JdbcExecutor {

    private static final int ORA_UNIQUE_VIOLATION = 1; // ORA-00001
    private static final int FETCH_SIZE = 100;         // Oracle'ın varsayılanı 10 satırdır.

    private final JdbcTransactionRunner transactions;

    public JdbcExecutor(JdbcTransactionRunner transactions) {
        this.transactions = Objects.requireNonNull(transactions, "JdbcTransactionRunner zorunludur");
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... params) {
        return execute(sql, connection -> {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setFetchSize(FETCH_SIZE);
                bind(statement, params);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<T> rows = new ArrayList<>();
                    while (resultSet.next()) {
                        rows.add(mapper.mapRow(resultSet));
                    }
                    return rows;
                }
            }
        });
    }

    public <T> Optional<T> queryForOptional(String sql, RowMapper<T> mapper, Object... params) {
        List<T> rows = query(sql, mapper, params);
        if (rows.size() > 1) {
            throw new DataAccessException("Tek satır beklenirken " + rows.size() + " satır döndü: " + sql);
        }
        return rows.stream().findFirst();
    }

    public int update(String sql, Object... params) {
        return execute(sql, connection -> {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                bind(statement, params);
                return statement.executeUpdate();
            }
        });
    }

    public long insertAndReturnKey(String sql, String keyColumn, Object... params) {
        return execute(sql, connection -> {
            try (PreparedStatement statement = connection.prepareStatement(sql, new String[] {keyColumn})) {
                bind(statement, params);
                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new DataAccessException("Üretilen anahtar okunamadı: " + sql);
                    }
                    return keys.getLong(1);
                }
            }
        });
    }

    private <R> R execute(String sql, SqlFunction<Connection, R> action) {
        try {
            return transactions.withConnection(action);
        } catch (SQLException e) {
            throw translate(sql, e);
        }
    }

    private static void bind(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    private static DataAccessException translate(String sql, SQLException e) {
        if (e.getErrorCode() == ORA_UNIQUE_VIOLATION) {
            return new DuplicateKeyException("Benzersizlik kısıtı ihlal edildi", e);
        }
        return new DataAccessException("SQL çalıştırılamadı: " + sql, e);
    }
}
