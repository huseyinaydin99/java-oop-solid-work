package tr.com.huseyinaydin.customer.infrastructure;

import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerRepository;
import tr.com.huseyinaydin.customer.domain.CustomerStatus;
import tr.com.huseyinaydin.customer.domain.Email;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.JdbcExecutor;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.RowMapper;
import tr.com.huseyinaydin.shared.persistence.DataAccessException;
import tr.com.huseyinaydin.shared.persistence.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class JdbcCustomerRepository implements CustomerRepository {

    private static final String SELECT_CUSTOMER =
            "SELECT id, full_name, email, status, created_at FROM customers";

    private static final String FIND_BY_ID = SELECT_CUSTOMER + " WHERE id = ?";
    private static final String FIND_BY_EMAIL = SELECT_CUSTOMER + " WHERE email = ?";
    private static final String FIND_PAGE =
            SELECT_CUSTOMER + " ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String FIND_BY_STATUS =
            SELECT_CUSTOMER + " WHERE status = ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String EXISTS_BY_EMAIL =
            "SELECT 1 FROM customers WHERE email = ? FETCH FIRST 1 ROWS ONLY";
    private static final String INSERT =
            "INSERT INTO customers (full_name, email, status, created_at) VALUES (?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE customers SET email = ?, status = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM customers WHERE id = ?";

    private final JdbcExecutor jdbc;
    private final RowMapper<Customer> mapper = new CustomerRowMapper();

    public JdbcCustomerRepository(JdbcExecutor jdbc) {
        this.jdbc = Objects.requireNonNull(jdbc, "JdbcExecutor zorunludur");
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jdbc.queryForOptional(FIND_BY_ID, mapper, id);
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        return jdbc.queryForOptional(FIND_BY_EMAIL, mapper, email.value());
    }

    @Override
    public List<Customer> findAll(PageRequest page) {
        return jdbc.query(FIND_PAGE, mapper, page.offset(), page.size());
    }

    @Override
    public List<Customer> findByStatus(CustomerStatus status, PageRequest page) {
        return jdbc.query(FIND_BY_STATUS, mapper, status.name(), page.offset(), page.size());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jdbc.queryForOptional(EXISTS_BY_EMAIL, resultSet -> true, email.value()).isPresent();
    }

    @Override
    public Customer save(Customer customer) {
        return customer.isNew() ? insert(customer) : update(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbc.update(DELETE_BY_ID, id) > 0;
    }

    private Customer insert(Customer customer) {
        long id = jdbc.insertAndReturnKey(INSERT, "ID",
                customer.fullName(),
                customer.email().value(),
                customer.status().name(),
                customer.createdAt());
        return customer.withId(id);
    }

    private Customer update(Customer customer) {
        int affectedRows = jdbc.update(UPDATE,
                customer.email().value(),
                customer.status().name(),
                customer.id());
        if (affectedRows == 0) {
            throw new DataAccessException("Güncellenecek müşteri bulunamadı: id=" + customer.id());
        }
        return customer;
    }
}
