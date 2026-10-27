package tr.com.huseyinaydin.shared.infrastructure.jdbc;

import tr.com.huseyinaydin.shared.persistence.DataAccessException;
import tr.com.huseyinaydin.shared.persistence.TransactionRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Supplier;

/** Bağlantıların yaşam döngüsünü ve transaction sınırlarını yönetir. */
public final class JdbcTransactionRunner implements TransactionRunner {

    private final DataSource dataSource;
    private final ThreadLocal<Connection> boundConnection = new ThreadLocal<>();

    public JdbcTransactionRunner(DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "DataSource zorunludur");
    }

    @Override
    public <R> R inTransaction(Supplier<R> work) {
        if (boundConnection.get() != null) {
            return work.get(); // Zaten bir transaction içindeyiz: ona katıl.
        }
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            boundConnection.set(connection);
            try {
                R result = work.get();
                connection.commit();
                return result;
            } catch (RuntimeException | Error | SQLException failure) {
                rollbackQuietly(connection, failure);
                throw failure;
            } finally {
                boundConnection.remove();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Transaction tamamlanamadı", e);
        }
    }

    /** Açık bir transaction varsa onun bağlantısını, yoksa havuzdan yeni bir bağlantı kullanır. */
    <R> R withConnection(SqlFunction<Connection, R> action) throws SQLException {
        Connection bound = boundConnection.get();
        if (bound != null) {
            return action.apply(bound); // Bağlantının sahibi transaction; burada kapatmıyoruz.
        }
        try (Connection connection = dataSource.getConnection()) {
            return action.apply(connection); // Transaction yok: autoCommit açık, tek ifadelik iş.
        }
    }

    private static void rollbackQuietly(Connection connection, Throwable original) {
        try {
            connection.rollback();
        } catch (SQLException rollbackFailure) {
            original.addSuppressed(rollbackFailure);
        }
    }
}
