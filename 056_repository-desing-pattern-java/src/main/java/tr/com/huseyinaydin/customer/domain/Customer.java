package tr.com.huseyinaydin.customer.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Customer {

    private final Long id;
    private final String fullName;
    private final LocalDateTime createdAt;
    private Email email;
    private CustomerStatus status;

    private Customer(Long id, String fullName, Email email,
                     CustomerStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = requireText(fullName);
        this.email = Objects.requireNonNull(email, "E-posta zorunludur");
        this.status = Objects.requireNonNull(status, "Durum zorunludur");
        this.createdAt = Objects.requireNonNull(createdAt, "Oluşturulma zamanı zorunludur");
    }

    /** Yeni bir müşteri doğar. Kimliğini henüz bilmiyoruz; onu veritabanı verecek. */
    public static Customer register(String fullName, Email email) {
        return new Customer(null, fullName, email, CustomerStatus.ACTIVE, LocalDateTime.now());
    }

    /** Daha önce kaydedilmiş bir müşteri veritabanından geri çağrılır. */
    public static Customer restore(long id, String fullName, Email email,
                                   CustomerStatus status, LocalDateTime createdAt) {
        return new Customer(id, fullName, email, status, createdAt);
    }

    public Customer withId(long newId) {
        if (!isNew()) {
            throw new IllegalStateException("Kimliği olan bir müşteriye yeni kimlik verilemez: " + id);
        }
        return new Customer(newId, fullName, email, status, createdAt);
    }

    public void deactivate() {
        if (status == CustomerStatus.PASSIVE) {
            throw new IllegalStateException("Müşteri zaten pasif: " + id);
        }
        status = CustomerStatus.PASSIVE;
    }

    public void changeEmail(Email newEmail) {
        this.email = Objects.requireNonNull(newEmail, "Yeni e-posta zorunludur");
    }

    public boolean isNew() {
        return id == null;
    }

    public Long id() { return id; }

    public String fullName() { return fullName; }

    public Email email() { return email; }

    public CustomerStatus status() { return status; }

    public LocalDateTime createdAt() { return createdAt; }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Henüz kimliği olmayan bir nesne de kendisine eşittir.
        }
        return other instanceof Customer that && id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer[id=%s, fullName=%s, email=%s, status=%s]"
                .formatted(id, fullName, email.value(), status);
    }

    private static String requireText(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Ad soyad boş olamaz");
        }
        return value.trim();
    }
}
