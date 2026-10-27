package tr.com.huseyinaydin.customer.infrastructure;

import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerStatus;
import tr.com.huseyinaydin.customer.domain.Email;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/** Bir CUSTOMERS satırını Customer nesnesine çevirir. Tek sorumluluğu budur. */
final class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet) throws SQLException {
        return Customer.restore(
                resultSet.getLong("id"),
                resultSet.getString("full_name"),
                new Email(resultSet.getString("email")),
                CustomerStatus.valueOf(resultSet.getString("status")),
                resultSet.getObject("created_at", LocalDateTime.class));
    }
}
