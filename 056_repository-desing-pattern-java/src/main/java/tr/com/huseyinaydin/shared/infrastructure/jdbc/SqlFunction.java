package tr.com.huseyinaydin.shared.infrastructure.jdbc;

import java.sql.SQLException;

/** java.util.function.Function'ın SQLException fırlatabilen hâli. */
@FunctionalInterface
interface SqlFunction<T, R> {

    R apply(T input) throws SQLException;
}
