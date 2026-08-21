### 🧱 Builder Design Pattern

Builder Pattern’ın ana amacı, çok sayıda opsiyonel parametre içeren karmaşık nesnelerin oluşturulmasını daha okunabilir ve kontrollü hâle getirmektir. Özellikle çok sayıda constructor parametresinin oluşturduğu telescoping constructor problemini çözerek nesne oluşturma sürecini esnek ve sürdürülebilir hâle getirir.

```java
public class User {
    
    private final String username;
    private final String email;
    private final String phone;
    private final String address;
    private final boolean active;
    
    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.active = builder.active;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private String username;
        private String email;
        private String phone;
        private String address;
        private boolean active = true;
        
        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder() {

        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

Bu yapıda zorunlu alanlar constructor'da, opsiyonel alanlar ise Builder metotlarında tanımlanarak nesnenin geçerli bir durumda oluşturulması sağlanır. Builder, nesnenin oluşturulma detaylarını çağıran koddan ayırarak okunabilirliği artırır ve yeni opsiyonel alanların eklenmesini kolaylaştırır.

```java
User user = User.builder("huseyin", "huseyinaydin99@gmail.com")
.phone("5551234567")
.address("Niğde - gel babana")
.active(true)
.build();
```

Burada User nesnesini doğrudan karmaşık bir constructor ile oluşturmak yerine, hangi değerlerin verildiğini adım adım ve açık biçimde ifade ettik. Böylece kodun okunabilirliği artarken parametrelerin sırasını hatırlama zorunluluğu da ortadan kalktı.

```java
/* ❌ Builder olmadan
new User("huseyin","huseyin@example.com","5551234567","Niğde",true);
*/
```

Parametre sayısı arttıkça bu yaklaşımın okunabilirliği düşer ve özellikle aynı türden parametrelerde yanlış değer gönderme riski artar. Builder ise her değeri anlamlı bir metot adıyla belirterek nesne oluşturma sürecini daha güvenli ve sürdürülebilir hâle getirir.

Kısaca:
🏗️ Builder = Karmaşık nesneleri adım adım, okunabilir ve esnek biçimde oluşturmak için vardır.

---

Lombok ile aynı Builder yapısını ciddi biçimde sadeleştirebiliriz; Builder Pattern'in mantığı değişmez, sadece Builder sınıfını ve tekrar eden kodu Lombok bizim için üretir.

### 🧱 Lombok ile Builder
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class User {


    private final String username;
    private final String email;
    private final String phone;
    private final String address;
    @Builder.Default
    private final boolean active = true;
}

@Builder, User.builder() yapısını ve gerekli builder metotlarını otomatik üretirken @Getter erişim metotlarını oluşturur. @Builder.Default ise active alanına değer verilmediğinde true değerinin korunmasını sağlar; böylece aynı Builder Pattern'i çok daha az boilerplate kodla uyguladım.

🚀 Kullanımı
User user = User.builder()
.username("huseyin")
.email("huseyin@example.com")
.phone("5551234567")
.address("Niğde")
.active(true)
.build();

Burada nesneyi oluştururken hangi alanın hangi değeri aldığını doğrudan gördüğümüz için kod daha okunabilir ve bakımı daha kolaydır. Lombok, Builder Pattern'in karmaşık nesneleri adım adım ve okunabilir biçimde oluşturma amacını korurken tekrar eden kodları ortadan kaldırdı.