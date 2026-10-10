package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/*
orphanRemoval = true (Customer tarafında): Bir müşteriye bağlı olan CustomerDetail
referansı ortadan kalktığında veya müşteri silindiğinde detayın veritabanından otomatik silinmesini sağlar.

CascadeType.ALL (veya REMOVE): EntityManager üzerinden bir Customer nesnesi remove()
ile silindiğinde, bu silme işleminin otomatik olarak CustomerDetail nesnesine de yayılmasını sağlar.

@OnDelete(action = OnDeleteAction.CASCADE): Hibernate tabloları otomatik oluştururken
(ddl-auto=update veya create) SQL sorgusuna FOREIGN KEY (...) REFERENCES ... ON DELETE CASCADE
kuralını ekler. Böylece JPA dışında doğrudan veritabanından bir müşteri silindiğinde bile detayı otomatik silinir.
*/

@Setter
@Getter
//@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "CUSTOMER_DETAILS")
public class CustomerDetail {

    /*
    // FIXME
    @OneToOne (cascade = CascadeType.PERSIST,orphanRemoval = true)
    @JoinColumn
    Customer customer;
    */

    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL eklendi
    @JoinColumn(name = "CUSTOMER_ID", columnDefinition = "INT UNIQUE")
    @OnDelete(action = OnDeleteAction.CASCADE) // Veritabanı (SQL) seviyesinde de ON DELETE CASCADE sağlar
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "foreign", strategy = "foreign",
            parameters = {
            @org.hibernate.annotations.Parameter(name = "property", value="customer")
             }
    )

    @Column(name = "CUSTOMER_DETAIL_ID")
    private int customerDetailId;

    @Column(name = "ADDRESS", length = 1000)
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL", length = 110)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATE")
    private Date createDate;
}