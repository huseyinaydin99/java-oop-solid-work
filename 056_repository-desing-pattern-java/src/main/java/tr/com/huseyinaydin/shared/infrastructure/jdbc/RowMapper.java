package tr.com.huseyinaydin.shared.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Bir satırı nesneye çevirir. */
@FunctionalInterface
public interface RowMapper<T> {

    T mapRow(ResultSet resultSet) throws SQLException;
}
