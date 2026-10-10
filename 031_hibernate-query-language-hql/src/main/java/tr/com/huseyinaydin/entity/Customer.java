package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.*;

// Entity - Sınıfının veritabanında bir tablo karşılığı vardır.
// Model - Sınıfının tablosu yoktur. Katmanlar arası veri taşımaktır.

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @OneToOne (mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true) // Müşteri silindiğinde detayın da silinmesini sağlar
    CustomerDetail customerDetail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", columnDefinition = "INT AUTO_INCREMENT FIRST")
    private int customerId;

    @Column(name = "FIRST_NAME", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL AFTER CUSTOMER_ID")
    private String firstName;

    @Column(name = "LAST_NAME", length = 120, columnDefinition = "VARCHAR(120) AFTER FIRST_NAME")
    private String lastName;

    @Column(name = "AGE", columnDefinition = "SMALLINT AFTER LAST_NAME")
    private short age;

    //FIXME tabloda kolonların sıralaması - YAPILDI
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", columnDefinition = "INT AUTO_INCREMENT FIRST")
    private int customerId;

    @Column(name = "FIRST_NAME", length = 100, nullable = false, columnDefinition = "VARCHAR(100) NOT NULL AFTER CUSTOMER_ID")
    private String firstName;

    @Column(name = "LAST_NAME", length = 120, columnDefinition = "VARCHAR(120) AFTER FIRST_NAME")
    private String lastName;

    @Column(name = "AGE", columnDefinition = "SMALLINT AFTER LAST_NAME")
    private short age;
    */

    /*
    Veri Tipi Dahil Edilmeli: columnDefinition kullandığınızda length = 100
    veya nullable = false gibi standart anotasyonlar bazı durumlarda yoksayılabilir;
    bu yüzden güvenli olması için VARCHAR(100) NOT NULL gibi ifadeleri doğrudan columnDefinition içine yazdık.

    Veritabanı Uyumluluğu: FIRST ve AFTER komutları MySQL ve MariaDB özelindedir.
    PostgreSQL veya Oracle gibi veritabanları bu komutları desteklemez ve hata verir.
    */

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}