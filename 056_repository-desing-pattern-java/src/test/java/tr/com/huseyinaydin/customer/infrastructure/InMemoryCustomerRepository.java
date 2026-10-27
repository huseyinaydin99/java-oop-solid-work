package tr.com.huseyinaydin.customer.infrastructure;

import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerRepository;
import tr.com.huseyinaydin.customer.domain.CustomerStatus;
import tr.com.huseyinaydin.customer.domain.Email;
import tr.com.huseyinaydin.shared.persistence.DataAccessException;
import tr.com.huseyinaydin.shared.persistence.DuplicateKeyException;
import tr.com.huseyinaydin.shared.persistence.PageRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/** CustomerRepository'nin testler için bellek içi ve sözleşmeye sadık uygulaması. */
public final class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(store.get(id)).map(InMemoryCustomerRepository::copyOf);
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        return store.values().stream()
                .filter(c -> c.email().equals(email))
                .findFirst()
                .map(InMemoryCustomerRepository::copyOf);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return findByEmail(email).isPresent();
    }

    @Override
    public List<Customer> findAll(PageRequest page) {
        return paginate(store.values().stream(), page);
    }

    @Override
    public List<Customer> findByStatus(CustomerStatus status, PageRequest page) {
        return paginate(store.values().stream().filter(c -> c.status() == status), page);
    }

    @Override
    public synchronized Customer save(Customer customer) {
        rejectDuplicateEmail(customer);
        if (!customer.isNew() && !store.containsKey(customer.id())) {
            throw new DataAccessException("Güncellenecek müşteri bulunamadı: id=" + customer.id());
        }
        Customer saved = customer.isNew() ? customer.withId(sequence.incrementAndGet()) : customer;
        store.put(saved.id(), copyOf(saved));
        return saved;
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    /** Oracle'daki UNIQUE kısıtının bellekteki karşılığı: aynı sözü burada da tutmalıyız. */
    private void rejectDuplicateEmail(Customer customer) {
        findByEmail(customer.email())
                .filter(existing -> !existing.id().equals(customer.id()))
                .ifPresent(existing -> {
                    throw new DuplicateKeyException("E-posta zaten kayıtlı: " + customer.email().value());
                });
    }

    /**
     * Veritabanı her okumada yeni bir nesne üretir; save çağrılmadan yapılan değişiklik kalıcı olmaz.
     * Saklanan nesneyle dışarıya verilen nesneyi ayırarak aynı davranışı burada da koruyoruz.
     */
    private static Customer copyOf(Customer customer) {
        return Customer.restore(customer.id(), customer.fullName(), customer.email(),
                customer.status(), customer.createdAt());
    }

    private static List<Customer> paginate(Stream<Customer> customers, PageRequest page) {
        return customers.sorted(Comparator.comparing(Customer::id))
                .skip(page.offset())
                .limit(page.size())
                .map(InMemoryCustomerRepository::copyOf)
                .toList();
    }
}
