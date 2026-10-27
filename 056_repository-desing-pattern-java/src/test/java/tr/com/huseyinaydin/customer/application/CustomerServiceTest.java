package tr.com.huseyinaydin.customer.application;

import org.junit.jupiter.api.Test;
import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerStatus;
import tr.com.huseyinaydin.customer.infrastructure.InMemoryCustomerRepository;
import tr.com.huseyinaydin.shared.persistence.PageRequest;
import tr.com.huseyinaydin.shared.persistence.TransactionRunner;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {

    /** Testte gerçek transaction yok: iş doğrudan çalıştırılır. */
    private static final TransactionRunner DIRECT = new TransactionRunner() {
        @Override
        public <R> R inTransaction(Supplier<R> work) {
            return work.get();
        }
    };

    private final CustomerService service = new CustomerService(new InMemoryCustomerRepository(), DIRECT);

    @Test
    void yeniMusteriKimlikAlirVeAktifBaslar() {
        Customer saved = service.register("Ayşe Yılmaz", "ayse@example.com");

        assertNotNull(saved.id());
        assertEquals(CustomerStatus.ACTIVE, saved.status());
    }

    @Test
    void ayniEpostaBuyukHarfleYazilsaBileIkinciKezKullanilamaz() {
        service.register("Ayşe Yılmaz", "ayse@example.com");

        assertThrows(EmailAlreadyInUseException.class,
                () -> service.register("Başka Biri", "AYSE@Example.com"));
    }

    @Test
    void pasifeAlinanMusteriAktifListedeGorunmez() {
        Customer ayse = service.register("Ayşe Yılmaz", "ayse@example.com");

        service.deactivate(ayse.id());

        assertTrue(service.activeCustomers(PageRequest.of(0, 10)).isEmpty());
    }
}
