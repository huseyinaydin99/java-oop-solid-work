package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

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

    /*
     Hakim taraf Order sınıfıdır.

     Çünkü veritabanındaki yabancı anahtarı (foreign key) barındıran ve @JoinColumn
     anotasyonuna sahip olan taraf odur; Customer sınıfındaki mappedBy özelliği de
     yönetimin Order sınıfında olduğunu teyit eder.

     mappedBy kullanılan sınıfı hakim yapmıyor muydu?

     Hayır, tam aksine mappedBy kullanılan sınıf ilişkinin zayıf (yönetilen) tarafı olur,
     yabancı anahtarı fiziksel olarak barındıran diğer sınıf ise hakim taraf kabul edilir.
    */
    // 1 - M
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT) // Bu anotasyon, ilişkili verilerin ana sorguyla birleştirilerek (JOIN) değil, ihtiyaç duyulduğunda ayrı bir SQL SELECT sorgusu çalıştırılarak veritabanından çekilmesini sağlar.
    private Set<Order> orders = new HashSet<>(); // M
    // List<Order> orders = new ArrayList<>();
    // Listeleri de kullanabilirdik.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 120)
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}