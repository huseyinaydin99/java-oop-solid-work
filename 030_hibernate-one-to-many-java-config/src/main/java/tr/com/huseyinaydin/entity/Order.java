package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ORDERS")
public class Order {

   // M - 1
   @ManyToOne (fetch = FetchType.LAZY)
   @Fetch(FetchMode.SELECT)
   @JoinColumn(name = "CUSTOMER_ID")
   private Customer customer;  // 1

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ORDER_ID", nullable = false)
   private int orderId;

   @Column(name = "PRODUCT", length = 100)
   private String product;

   @Column (name = "CODE", length = 50)
   private String  code;

   // bu sütunun ondalık kısmında 2 basamak olmak üzere toplamda en fazla 7 basamaklı (örneğin maksimum 99999.99) sayısal bir değer tutabileceğini belirtir.
   @Column (name = "AMOUNT", precision = 7, scale = 2)  // 1_000_000.00 örnektir
   private BigDecimal amount;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "DATE_CREATE", length = 40)
   private Date createdDate;
}