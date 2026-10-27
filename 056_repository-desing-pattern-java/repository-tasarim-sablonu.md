# Repository Tasarım Şablonu (Design Pattern)

Repository Tasarım Şablonu, **iş mantığı (Service katmanı)** ile **veritabanı (Data Access katmanı)** arasına çekilen temiz bir sınır çizgisidir.

Temel amacı şudur: **İş katmanı veritabanının ne olduğunu, hangi SQL sorgusunun çalıştığını veya verinin nereden geldiğini bilmemelidir.**

İş katmanı Repository'ye baktığında karşısında bir veritabanı değil, sanki RAM'de duran sıradan bir Java listesi (**Collection**) görür. *"Şu nesneyi kaydet"*, *"ID'si 5 olanı getir"* der ve gerisine karışmaz.

---

## Aşama 1: Repository Olmasaydı Ne Olurdu? (Sorunu Görelim)

Diyelim ki bir kullanıcı kayıt işlemin var. Repository kullanmazsan `UserService` sınıfın şöyle görünür:

```java
public class BadUserService {
    public void registerUser(String name, String email) {
        // İş kuralı
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Geçersiz e-posta!");
        }

        // Ve aniden iş katmanının ortasında SQL / JDBC kodu başlar:
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://...");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Buradaki 3 büyük felaket

1. Yarın tablo adı veya veritabanı (PostgreSQL'den MongoDB'ye) değişirse **tüm Service sınıflarını baştan yazman** gerekir.
2. Bu sınıfa **birim testi (Unit Test) yazamazsın**; çünkü metodu her çağırdığında gerçek veritabanına bağlanmaya çalışır.
3. İş kuralları ile SQL sorguları **çorba olmuştur**.

Şimdi bunu Repository şablonuyla adım adım temizleyelim.

---

## Aşama 2: Varlık Sınıfı (Domain / Entity)

Önce üzerinde çalışacağımız saf veri nesnesini (`User`) oluşturalım. İçinde hiçbir veritabanı kütüphanesi yok, sadece saf Java:

```java
public class User {
    private Long id;
    private String name;
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

---

## Aşama 3: Sözleşme (Repository Arayüzü - Interface)

Şablonun kalbi burasıdır. İş katmanının veriyle yapabileceği işlemleri bir `interface` olarak tanımlarız.

> **Kritik Nokta:** Bu arayüzün içinde `SQL`, `Connection`, `Hibernate` veya `ResultSet` gibi veritabanına ait tek bir kavram bile geçmez.

```java
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
```

---

## Aşama 4: Mutfağın İçi (Somut Uygulamalar)

Artık elimizde bir sözleşme (`UserRepository`) var. Bu sözleşmeyi istediğimiz teknolojiyle doldurabiliriz. Olayın gücünü görmek için iki farklı uygulama yazalım:

### 1. Hafıza İçi Uygulama (Testler veya Önbellek İçin)

Gerçek veritabanı kurmadan, verileri RAM'deki bir `Map` içinde tutan sınıf:

```java
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> database = new HashMap<>();

    @Override
    public void save(User user) {
        database.put(user.getId(), user);
        System.out.println("RAM'e kaydedildi: " + user.getName());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
```

### 2. Gerçek Veritabanı Uygulaması (JDBC / SQL İçin)

Aynı sözleşmenin SQL konuşan versiyonu:

```java
import java.util.*;

public class PostgresUserRepository implements UserRepository {

    @Override
    public void save(User user) {
        // Burada gerçek INSERT INTO users ... SQL sorgusu çalışır
        System.out.println("PostgreSQL veritabanına kaydedildi: " + user.getName());
    }

    @Override
    public Optional<User> findById(Long id) {
        // Burada SELECT * FROM users WHERE id = ? çalışır
        return Optional.of(new User(id, "Hüseyin", "huseyin@mail.com"));
    }

    @Override
    public List<User> findAll() {
        return List.of(); // SELECT * FROM users
    }

    @Override
    public void deleteById(Long id) {
        // DELETE FROM users WHERE id = ?
    }
}
```

---

## Aşama 5: İş Katmanını Bağlamak (Service Layer)

Şimdi `UserService` sınıfımıza geri dönelim. Artık SQL bilmiyor; sadece `UserRepository` arayüzünü tanıyor:

```java
public class UserService {
    // Sadece interface'e bağımlıyız (Dependency Inversion)
    private final UserRepository userRepository;

    // Dışarıdan hangi repository verilirse onunla çalışır (Constructor Injection)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(Long id, String name, String email) {
        // Sadece saf iş kuralları:
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Geçersiz e-posta adresi!");
        }

        User newUser = new User(id, name, email);

        // Nereye kaydettiğini bilmez, sadece "kaydet" der:
        userRepository.save(newUser);
    }
}
```

### Sihrin Gerçekleştiği An (Main)

`UserService` içindeki **tek bir satır kodu bile değiştirmeden** veritabanını nasıl değiştirdiğimize bak:

```java
public class Main {
    public static void main(String[] args) {
        // 1. Senaryo: Test yaparken RAM'de çalıştır
        UserRepository ramRepo = new InMemoryUserRepository();
        UserService testService = new UserService(ramRepo);
        testService.registerUser(1L, "Ahmet", "ahmet@mail.com");

        // 2. Senaryo: Canlı ortamda (Production) PostgreSQL'e geçir
        UserRepository sqlRepo = new PostgresUserRepository();
        UserService prodService = new UserService(sqlRepo);
        prodService.registerUser(2L, "Mehmet", "mehmet@mail.com");
    }
}
```

---

## Aşama 6: Kendini Tekrar Etmemek (Generic Repository)

Projede sadece `User` olmaz; `Product`, `Order`, `Category` gibi onlarca sınıf olur. Hepsine ayrı ayrı `save`, `findById`, `deleteById` yazmak yerine **Java Generics** (`<T, ID>`) kullanarak ortak bir şablon çıkarılır:

```java
import java.util.List;
import java.util.Optional;

// T: Entity tipi (User, Product vb.), ID: Kimlik tipi (Long, UUID vb.)
public interface CrudRepository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
```

Artık `UserRepository` oluştururken temel metotları tekrar yazmazsın, sadece kullanıcıya özel ekstra bir sorgun varsa onu eklersin:

```java
public interface UserRepository extends CrudRepository<User, Long> {
    // Temel CRUD metotları yukarıdan miras geldi bile!
    // Sadece User'a özel metodu buraya ekliyoruz:
    Optional<User> findByEmail(String email);
}
```

---

## Spring Data JPA ile Bağlantı

Spring Data JPA kullandığında yaptığın şu işlemin arka planındaki mimari tam olarak budur:

```java
public interface UserRepository extends JpaRepository<User, Long> { }
```

Sen **Aşama 3 ve 6**'daki arayüzü yazarsın; Spring ise **Aşama 4**'teki somut sınıfı çalışma zamanında (**Dynamic Proxy** ile) senin yerine otomatik üretir.
