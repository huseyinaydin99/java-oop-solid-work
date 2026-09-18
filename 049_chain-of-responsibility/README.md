### Chain of Responsibility (Sorumluluk Zinciri);

Chain of Responsibility (Sorumluluk Zinciri), bir isteği işleyebilecek nesneleri zincirleme birbirine bağlayarak, isteğin doğru işleyiciyi bulana veya tüm adımlardan başarıyla geçene kadar sırayla aktarılmasını sağlar. Bu desen sayesinde gönderici ve alıcı arasındaki sıkı bağımlılığı ortadan kaldırır, kodunuzu esnek, modüler ve kolayca genişletilebilir hale getiririz.

Örnek Senaryo: Bir sisteme giriş yapmaya çalışan kullanıcının isteğini denetleyen (Boşluk Kontrolü -> Yetki Kontrolü) bir güvenlik zinciri.

```java
class Request {
    private String username;
    private String role;

    public Request(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}
```

📦 Öncelikle sistemde dolaşacak olan ve içinde kullanıcının kimlik bilgilerini taşıyan temel istek nesnemizi oluşturuyoruz. Bu basit veri sınıfı, zincirdeki her bir halkanın üzerinde inceleme yapıp karara varacağı hammaddemizi temsil ediyor. 🎯

```java
abstract class BaseHandler {
    protected BaseHandler nextHandler;

    public void setNext(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(Request request);

    protected boolean checkNext(Request request) {
        if (nextHandler == null) {
            return true;
        }
        return nextHandler.handle(request);
    }
}
```

🔗 Zincirimizin esnek iskeletini oluşturan bu soyut sınıf, kendinden sonra gelecek olan delegasyon halkasını bilmek ve gerektiğinde topu ona atmakla görevlidir. Kendi adımını başarıyla geçtiğinde süreci tıkanmadan bir sonraki uzmana aktararak, sistem içinde muhteşem ve kesintisiz bir takım oyunu kurar. 🤝

```java
class UserValidationHandler extends BaseHandler {
    @Override
    public boolean handle(Request request) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            System.out.println("❌ Hata: Kullanıcı adı boş olamaz!");
            return false;
        }
        System.out.println("✅ Kullanıcı adı geçerli, yetki kontrolüne geçiliyor...");
        return checkNext(request);
    }
}

class RoleCheckHandler extends BaseHandler {
    @Override
    public boolean handle(Request request) {
        if (!"ADMIN".equals(request.getRole())) {
            System.out.println("❌ Erişim Reddedildi: Sadece yöneticiler girebilir!");
            return false;
        }
        System.out.println("✅ Yetki onaylandı, sisteme giriş yapıldı!");
        return checkNext(request);
    }
}
```

🛡️ Bu somut işleyiciler zincirimizin gerçek kahramanlarıdır; her biri sadece kendi sorumluluk alanına odaklanıp tek bir iş kuralını büyük bir titizlikle denetler. Eğer şartlar sağlanmazsa süreci anında keserek güvenliği sağlarken, her şey yolundaysa işi güvenle sıradaki arkadaşına devreder. 🚀

```java
public class Main {
    public static void main(String[] args) {
        BaseHandler validationHandler = new UserValidationHandler();
        BaseHandler roleHandler = new RoleCheckHandler();

        // Zinciri kuruyoruz: Doğrulama -> Yetki Kontrolü
        validationHandler.setNext(roleHandler);

        // İsteği oluşturup zincirin sadece ilk halkasına veriyoruz
        System.out.println("--- Birinci Deneme (Yetkisiz Kullanıcı) ---");
        Request request1 = new Request("ahmet", "USER");
        validationHandler.handle(request1);

        System.out.println("\n--- İkinci Deneme (Yetkili Kullanıcı) ---");
        Request request2 = new Request("ayse", "ADMIN");
        validationHandler.handle(request2);
    }
}
```

🧩 Son aşamada yapbozun parçalarını birleştirerek halkaları birbirine takıyor ve otonom işleyen mükemmel kontrol mekanizmamızı tek bir noktadan tetikliyoruz. Artık sisteme yeni bir güvenlik katmanı eklemek istediğimizde mevcut kodlara dokunmadan sadece araya yeni bir halka takmamız yeterli olacak! ✨

### Yararları;

Otomatik İlerleyiş: Biz sadece ilk halkayı çağırdıktan sonra, o kendi görevini başarıyla tamamlarsa checkNext sayesinde topu zincirdeki bir sonraki halkaya (bizim örneğimizde roleHandler) kendisi aktarır.

Merkezi Kontrolden Kurtulma: Zincirde 10 farklı adım (IP kontrolü, Captcha, İki Adımlı Doğrulama vs.) da olsa, Main sınıfı sadece ilk adımı bilir. Diğer 9 adımı tek tek elle çağırmak veya Main içine iç içe 10 tane if-else bloğu yazmak zorunda kalmayız.

### Açıklamalar;

UserValidationHandler sınıfı, kendinden sonra gelecek olanın RoleCheckHandler olduğunu doğuştan (kodlanırken) bilmez; bunu ona program çalışırken biz öğretiriz. 🧠

Bu bağlantı ve sihir, Main sınıfında zinciri inşa ederken yazdığımız şu tek satırlık kodda gizlidir:

validationHandler.setNext(roleHandler);

İşte arka planda tam olarak şu 2 adım gerçekleşiyor:

🔗 Adres Teslimi: Biz setNext metodunu çağırdığımızda, BaseHandler sınıfının içindeki boş duran nextHandler değişkenine roleHandler nesnesini yerleştiririz. Yani birinci halkaya, "Kendi işini bitirince pas atacağın takım arkadaşın budur" diyerek adresini sıkıca tembihlemiş oluruz.

🚀 Otomatik Pas: UserValidationHandler kendi doğrulamasını başarıyla geçip checkNext() metodunu çağırdığında, bu metod içerisindeki nextHandler.handle() satırı çalışır. Elinde zaten roleHandler'ın adresi olduğu için, isteği doğrudan ve otonom bir şekilde ona iletir.

Özetle; sınıflar (halkalar) birbirini tanımaz ve birbirine sıkı sıkıya bağlı (tightly coupled) değildir. Kimin kime top atacağını, zincirin yöneticisi (Main) adeta Lego parçalarını birbirine takar gibi dışarıdan ve tamamen esnek bir şekilde belirler! 🧩✨