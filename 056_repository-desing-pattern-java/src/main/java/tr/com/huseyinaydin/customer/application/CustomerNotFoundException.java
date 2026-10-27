package tr.com.huseyinaydin.customer.application;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(long customerId) {
        super("Müşteri bulunamadı: id=" + customerId);
    }
}
