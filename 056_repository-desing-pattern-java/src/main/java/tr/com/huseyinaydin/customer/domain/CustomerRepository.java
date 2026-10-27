package tr.com.huseyinaydin.customer.domain;

import tr.com.huseyinaydin.shared.persistence.PageRequest;
import tr.com.huseyinaydin.shared.persistence.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends Repository<Customer, Long> {

    Optional<Customer> findByEmail(Email email);

    List<Customer> findByStatus(CustomerStatus status, PageRequest page);

    boolean existsByEmail(Email email);
}
