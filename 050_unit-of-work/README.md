### Unit of Work Desing Pattern;

Unit of Work (İş Birimi) tasarım kalıbı, bir iş süreci (transaction) boyunca veritabanında yapılması gereken tüm okuma, yazma ve güncelleme işlemlerini koordine ederek, sürecin sonunda ya tüm değişikliklerin tek seferde kaydedilmesini (commit) ya da hata anında hiçbirinin uygulanmamasını (rollback) sağlar. Bu kalıbı en iyi anlatan senaryo, iki hesap arasındaki para transferi işlemidir.

### Unit of Work nedir?
Unit of Work, veritabanında yapacağım birden fazla değişikliği tek bir paket halinde toplayıp aynı anda işleyen merkezi bir yönetim kalıbıdır. Bu sayede, sürecin başından sonuna kadar yaptığım tüm işlemler ya hep birlikte başarıyla kaydedilir ya da bir hata anında hiçbir şey olmamış gibi tamamen geri alınır.

### Ne değildir?
Sadece basit bir veritabanı bağlantısı açma veya tekil bir SQL sorgusunu çalıştırma aracı kesinlikle değildir. Her bir repozitorynin kendi başına buyruk hareket edip anında veritabanına kayıt attığı, dağınık ve veri bütünlüğünü riske atan kontrolsüz bir yapı asla değildir.

### Ne amaca hizmet eder?
Temel amacım, uygulamamın hafızasındaki iş mantığı değişiklikleri ile veritabanındaki fiziksel kayıtlar arasındaki tutarlılığı yüzde yüz oranında garanti altına almaktır. Veri karmaşasını önleyerek, kodumun veritabanıyla olan o yoğun iletişimini tek bir merkezden, adeta bir orkestra şefi gibi uyum içinde yönetmeme hizmet eder.

### Hangi sorunlara çözüm getirir?
Çoklu tablo güncellemeleri sırasında sistemin veya bağlantının aniden çökmesiyle oluşan, veritabanını çöplüğe çeviren "yarım kalmış veri" felaketini kökünden çözer. Ayrıca, her küçük kayıt işlemi için veritabanına defalarca gidip gelmenin ve sürekli bağlantı açıp kapatmanın yarattığı performans darboğazlarını ortadan kaldırarak sistemi rahatlatır.

### Hangi durumlarda kullanılmalıdır?
Birden fazla veritabanı tablosunu veya repozitory'yi aynı anda, birbirine sıkı sıkıya bağımlı bir şekilde güncellemem gereken karmaşık iş akışlarında bu kalıbı mutlaka kullanırım. Sistemin hata toleransının sıfır olduğu ve "ya hep ya hiç" kuralının tavizsiz bir şekilde işlemesi gereken her kritik durumda tereddütsüz devreye sokarım.

### Hangi senaryolara uygundur?
Bir hesaptan bakiye düşerken diğerine ekleme yaptığım ve asla hata kabul etmeyen finansal para transferi işlemleri tam bu yapının kalemidir. Aynı şekilde, bir e-ticaret sisteminde sipariş oluştururken hem sepeti boşalttığım, hem stok miktarını düşürdüğüm hem de ödeme kaydını aynı saniye içinde oluşturduğum karmaşık alışveriş senaryoları için biçilmiş kaftandır.

---

#### Örnek;

Öncelikle üzerinde çalışacağımız hesap modelini ve veritabanı işlemlerini soyutlayacak arayüzü tanımlıyoruz.

```java
public class Account {
    private String id;
    private double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    // Getter ve Setter metodları...
    public void withdraw(double amount) { this.balance -= amount; }
    public void deposit(double amount) { this.balance += amount; }
}

public interface AccountRepository {
    Account findById(String id);
    void update(Account account);
}
```

🗂️ Veritabanı işlemlerini soyutlayan bu repozitory arayüzü, hesap bilgilerini yönetmemiz için bize esnek ve temiz bir sözleşme sunar. Böylece iş mantığımızı yazarken, verilerin SQL tarafında nasıl kaydedildiğiyle uğraşmak yerine sadece uygulamanın ne yapması gerektiğine odaklanabiliriz.

#### Unit of Work Arayüzü;

Tüm veritabanı işlemlerini yönetecek olan ana işlem yöneticimizin sözleşmesini oluşturuyoruz.

```java
public interface UnitOfWork extends AutoCloseable {
    AccountRepository getAccountRepository();
    void commit() throws Exception;
    void rollback() throws Exception;
}
```

🤝 Tüm veritabanı repozitory'lerini tek bir şemsiye altında toplayan bu arayüz, işlemlerin ya hep beraber tamamlanmasını ya da hata anında tamamen geri alınmasını garanti altına alır. Veri tutarlılığını koruyan bu merkezi yapı sayesinde, sisteminizdeki her bir fonksiyonel adım güvenli bir iş paketine dönüşür.

#### Gerçek Veritabanı Bağlantısı (JDBC Implementation)

Gerçek bir veritabanı bağlantısı (java.sql.Connection) kullanarak bu arayüzü hayata geçiriyoruz. Repozitory'nin de aynı bağlantıyı kullanması burada kritik noktadır.

```java
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUnitOfWork implements UnitOfWork {
    private final Connection connection;
    private AccountRepository accountRepository;

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false); // Kontrolü veritabanından alıyoruz
    }

    @Override
    public AccountRepository getAccountRepository() {
        if (accountRepository == null) {
            // Repozitory'ye aynı connection nesnesini veriyoruz
            accountRepository = new JdbcAccountRepository(connection); 
        }
        return accountRepository;
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
```

⚙️ Gerçek bir JDBC bağlantısı üzerinden auto-commit özelliğini kapatan bu sınıf, işlem kontrolünü tamamen veritabanından alıp sizin mimarinizin ellerine bırakır. İşlemler başarıyla bittiğinde commit ile verileri kalıcı hale getirirken, en ufak bir pürüzde rollback ile tüm değişiklikleri iptal ederek veritabanınızın kalbini korur.

#### İş Mantığında Kullanım (Business Logic)
   Oluşturduğumuz bu yapıyı, para transferi yapan bir servis sınıfında kullanıyoruz.

```java
public class MoneyTransferService {
    // Veritabanı bağlantısı üreten bir metod (örneğin DataSource üzerinden)
    private ConnectionProvider connectionProvider; 

    public void transfer(String fromId, String toId, double amount) {
        try (UnitOfWork uow = new JdbcUnitOfWork(connectionProvider.getConnection())) {
            AccountRepository repo = uow.getAccountRepository();
            
            Account fromAccount = repo.findById(fromId);
            Account toAccount = repo.findById(toId);
            
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            
            repo.update(fromAccount);
            repo.update(toAccount); // Bu noktada henüz veritabanına yazılmadı
            
            uow.commit(); // Her şey yolunda, tüm değişiklikleri tek seferde kaydet
            System.out.println("Transfer başarıyla gerçekleşti!");
            
        } catch (Exception e) {
            // uow.close() AutoCloseable sayesinde otomatik çağrılsa da,
            // Hata durumunda rollback mekanizması devreye girmelidir.
            System.err.println("Transfer sırasında hata oluştu, işlem geri alınıyor.");
            // Loglama ve rollback yönetimi...
        }
    }
}
```

🏦 Para transferi gibi kritik bir işlemi gerçekleştirirken, iki farklı hesabın bakiyesinin güncellenmesi sürecini hiçbir veri kaybı riski olmadan kusursuzca yönetiyoruz. Beklenmedik bir kriz anında veya kodun herhangi bir satırında hata fırlatıldığında sistem anında işlemi iptal ederek, ne paranın buharlaşmasına ne de yanlış hesaba gitmesine izin veriyor.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionProvider {
    Connection getConnection() throws SQLException;
}
```

```java
import java.sql.Connection;
import java.sql.DriverManager;

// Gerçek veritabanı bağlantısını kuran somut sınıfımız
public class SimpleConnectionProvider implements ConnectionProvider {
    private final String url = "jdbc:mysql://localhost:3306/bank_db"; // Kendi DB url'iniz
    private final String user = "root";
    private final String password = "toor";

    @Override
    public Connection getConnection() throws SQLException {
        // DriverManager üzerinden gerçek ve yeni bir bağlantı açıyoruz
        return DriverManager.getConnection(url, user, password); 
    }
}
```

🔌 Veritabanı ile uygulamamız arasında güvenli bir köprü kuran bu bağlantı sağlayıcısı, altyapısal bağlantı detaylarını iş mantığından zarifçe gizleyerek kod karmaşasını önler. Sadece ihtiyacımız olan saf bağlantı nesnesini bize sunarak, yarın veritabanı teknolojinizi değiştirseniz bile ana mimarinizin hiç etkilenmeden ayakta kalmasını sağlar.

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAccountRepository implements AccountRepository {
    private final Connection connection;

    // Bağlantıyı Unit of Work'ten alıyoruz ki aynı transaction içinde çalışsın
    public JdbcAccountRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account findById(String id) {
        String sql = "SELECT id, balance FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("id"), rs.getDouble("balance"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Veritabanından hesap okunurken hata oluştu: " + id, e);
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Hesap güncellenirken hata oluştu: " + account.getId(), e);
        }
    }
}
```

```java
import java.sql.Connection;
import java.sql.SQLException;

public interface UnitOfWork extends AutoCloseable {
    AccountRepository getAccountRepository();
    void commit() throws SQLException;
    void rollback() throws SQLException;
}

public class JdbcUnitOfWork implements UnitOfWork {
    private final Connection connection;
    private AccountRepository accountRepository;

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    @Override
    public AccountRepository getAccountRepository() {
        if (accountRepository == null) {
            accountRepository = new JdbcAccountRepository(connection);
        }
        return accountRepository;
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
```

⚙️ Tüm veritabanı işlemlerini tek bir şemsiye altında toplayan bu merkezi yönetici, işlemlerin ya hep beraber uygulanmasını ya da hata anında kusursuzca geri alınmasını garanti eder. Auto-commit özelliğini devreden çıkarıp dizginleri eline alarak, sisteminizdeki her bir fonksiyonel adımı parçalanamaz bütün bir iş paketine dönüştürür.

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAccountRepository implements AccountRepository {
    private final Connection connection;

    public JdbcAccountRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account findById(String id) {
        String sql = "SELECT id, balance FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getString("id"), rs.getDouble("balance"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Hesap okuma hatası: " + id, e);
        }
        return null;
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Hesap güncelleme hatası: " + account.getId(), e);
        }
    }
}
```

🗄️ Merkezi yöneticiden gelen ortak bağlantıyı sadakatle kullanan bu sınıf, çalıştırdığı tüm sorguların aynı "transaction" çatısı altında nefes almasını sağlar. Kendi başına hareket edip veri bütünlüğünü bozabilecek her operasyon, bu sayede senkronize bir orkestranın uyumlu bir parçasına dönüşerek veri tutarsızlıklarının önüne geçer.

```java
public class MoneyTransferService {
    private final ConnectionProvider connectionProvider;

    public MoneyTransferService(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void transfer(String fromId, String toId, double amount) {
        try (UnitOfWork uow = new JdbcUnitOfWork(connectionProvider.getConnection())) {
            AccountRepository repo = uow.getAccountRepository();
            
            Account fromAccount = repo.findById(fromId);
            Account toAccount = repo.findById(toId);
            
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            
            repo.update(fromAccount);
            repo.update(toAccount);
            
            uow.commit();
            System.out.println("Transfer başarıyla gerçekleşti, veriler kalıcı hale getirildi!");
            
        } catch (Exception e) {
            System.err.println("Kritik hata! Veri kaybını önlemek için işlemler geri alınıyor: " + e.getMessage());
            // UOW auto-closeable olduğu için exception fırladığında kapanır, 
            // ancak production kodunda explicit rollback yönetimi de yapılmalıdır.
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ConnectionProvider provider = new SimpleConnectionProvider();
        MoneyTransferService transferService = new MoneyTransferService(provider);
        
        System.out.println("Sistem ayağa kalktı, güvenli para transferi başlatılıyor...");
        transferService.transfer("HESAP_A", "HESAP_B", 1500.0);
    }
}
```

🏦 Kritik iş süreçlerini yöneten bu yapı, beklenmedik kriz anlarında veya kodun herhangi bir satırında pürüz çıktığında anında kalkanlarını kaldırarak eksik kalmış değişikliklerin veritabanına yansımasını durdurur. Bileşenlerin birbirine dışarıdan bağlanmasıyla (Dependency Injection) hayat bulan bu esnek mimari, yazılımınızı gerçek dünyadaki olası veri facialarından koruyan yıkılmaz bir kale inşa eder.

---

### Sistemlerin Ortak Ruhu: Process ve Transaction Mimarisindeki Derin Benzerlik;
İşletim sistemleri process'leri, veritabanları ise transaction'ları yönetirken aslında "eşzamanlı işlemlerin kaosa yol açmadan nasıl güvenle yürütüleceği" şeklindeki evrensel mühendislik problemini çözerler. Her ikisi de çalıştırdıkları görevlere, dış dünyadan tamamen soyutlanmış ve sanki sistemin tek sahibi onlarmış gibi hissettiren izole bir güvenli hareket alanı sunar. Kaynak paylaşımı sırasında process'ler donanım için, transaction'lar ise veri satırları için kilit (lock) mekanizmaları kullanarak yarışır ve her iki ekosistem de aynı kilitlenme (deadlock) krizleriyle yüzleşmek zorundadır. Olası bir felaket veya çökme anında ise; işletim sistemi tahsis ettiği belleği anında temizleyerek, veritabanı da yarım kalan işlemleri geriye sararak (rollback) sistemin kararlı ve lekesiz yapısını tavizsizce korur.

### Meşru Şemsiyenin Altındaki Hayaletler: Parazit İşlemler
Windows'ta sinsi bir virüsün kendini yeni bir process olarak ifşa etmek yerine, halihazırda çalışan güvenilir bir sürecin damarlarına sızarak sessizce işini görmesini veritabanı dünyasındaki transaction zafiyetlerine çok benzetiyorum. Tıpkı o virüsler gibi, veritabanında da meşru ve sıradan görünen bir transaction'ın içine sızdırılmış zararlı bir SQL enjeksiyonu veya sinsi bir tetikleyici (trigger), kendi başına bağımsız bir işlem başlatmadan doğrudan o güvenilir kılıfın altında çalışarak amacına hayalet gibi ulaşıyor.

### Spring'in Mevcut Sürece Dahil Olma Mekanizması: Propagation
Spring ekosisteminde, bir işlemin kendi başına yeni bir süreç başlatmak yerine halihazırda çalışan aktif bir transaction'a katılarak onun ayrılmaz bir parçasıymış gibi yürütülmesini sağlayan bu kavrama Transaction Propagation (İşlem Yayılımı) diyoruz.