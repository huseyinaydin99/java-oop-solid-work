package tr.com.huseyinaydin.customer.application;

import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerRepository;
import tr.com.huseyinaydin.customer.domain.CustomerStatus;
import tr.com.huseyinaydin.customer.domain.Email;
import tr.com.huseyinaydin.shared.persistence.DuplicateKeyException;
import tr.com.huseyinaydin.shared.persistence.PageRequest;
import tr.com.huseyinaydin.shared.persistence.TransactionRunner;

import java.util.List;
import java.util.Objects;

public final class CustomerService {

    private final CustomerRepository customers;
    private final TransactionRunner transactions;

    public CustomerService(CustomerRepository customers, TransactionRunner transactions) {
        this.customers = Objects.requireNonNull(customers, "CustomerRepository zorunludur");
        this.transactions = Objects.requireNonNull(transactions, "TransactionRunner zorunludur");
    }

    public Customer register(String fullName, String rawEmail) {
        Email email = new Email(rawEmail);
        return transactions.inTransaction(() -> {
            if (customers.existsByEmail(email)) {
                throw new EmailAlreadyInUseException(email);
            }
            try {
                return customers.save(Customer.register(fullName, email));
            } catch (DuplicateKeyException raceCondition) {
                throw new EmailAlreadyInUseException(email, raceCondition);
            }
        });
    }

    public void deactivate(long customerId) {
        transactions.runInTransaction(() -> {
            Customer customer = customers.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(customerId));
            customer.deactivate();
            customers.save(customer);
        });
    }

    public List<Customer> activeCustomers(PageRequest page) {
        return customers.findByStatus(CustomerStatus.ACTIVE, page);
    }
}
