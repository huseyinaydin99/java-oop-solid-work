### Nedir?
Proxy tasarım şablonu, bir nesneye erişimi kontrol etmek veya yönetmek için onun yerine geçen bir vekil veya temsilci nesne kullanmamızı sağlayan yapısal bir desendir. Bu desen sayesinde, asıl nesnenin davranışlarını değiştirmeden onun üzerinde ekstra bir katman oluşturarak istemci ile asıl nesne arasındaki iletişimi dolaylı hale getiririz.

### Ne değildir?
Proxy, asıl nesnenin iş mantığını veya temel sorumluluklarını tamamen değiştiren, ona yepyeni bağımsız özellikler ekleyen bir Decorator (Dekoratör) deseni değildir. Aynı şekilde, karmaşık alt sistemleri tek bir arayüz arkasına gizleyerek sistemi basitleştirmeyi amaçlayan bir Facade (Cephe) şablonu olarak da düşünülemez.

### Ne amaçla kullanılır?
Esas amacımız, asıl nesneye yapılan çağrıları araya girerek yakalamak ve bu esnada yetkilendirme, önbelleğe alma veya geciktirilmiş yükleme gibi ekstra işlemleri gerçekleştirmektir. Böylece, sistem kaynaklarını daha verimli kullanmayı ve asıl nesnenin güvenliğini veya yaşam döngüsünü dış dünyadan izole etmeyi hedefleriz.

### Hangi problemlere çözüm getirir?
Bellek tüketimi yüksek olan ağır nesnelerin sistemin başında gereksiz yere yaratılması sorununu ortadan kaldırarak uygulama performansını artırır. Ayrıca, hassas nesnelere doğrudan erişimi kısıtlayarak yetkisiz kullanımların önüne geçer ve uzak sunuculardaki nesnelere erişirken ağ bağlantı karmaşıklığını istemciden gizler.

### Hangi senaryolarda kullanılır?
Veritabanından büyük boyutlu verileri veya yüksek çözünürlüklü görselleri yalnızca kullanıcı gerçekten görmek istediğinde belleğe yüklemek (Virtual Proxy) istediğimiz senaryolarda sıklıkla tercih ederiz. Bunun yanında, sadece yetkili kullanıcıların veya adminlerin belirli kritik metodları çalıştırmasına izin verdiğimiz güvenlik tabanlı yetkilendirme (Protection Proxy) durumlarda da aktif olarak kullanırız.

### Kod Örneği: Geciktirilmiş Yükleme (Virtual Proxy) Senaryosu 

```java
public interface Image {
    void display();
}
```

Sistemimde hem gerçek nesnenin hem de vekil nesnenin ortak bir dili konuşabilmesi için bu temel arayüzü tanımladım. İstemcinin sadece bu arayüzü bilmesini sağlayarak, arka planda hangi sınıfın veya mantığın çalıştığını ondan tamamen gizlemiş oluyorum.

```java
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Diskten ağır bir şekilde yükleniyor: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Ekranda gösteriliyor: " + fileName);
    }
}
```

Ağır ve maliyetli işlemleri barındıran asıl iş sınıfımı oluştururken, nesne belleğe alındığı an diskten yükleme yapacak şekilde tasarladım. Bu sınıfı doğrudan kullanmak yerine bir vekil arkasına saklayacağım ki, bu yüksek maliyetli yükleme işlemi sadece gerçekten ekranda gösterim yapılacağı zaman gerçekleşsin.

```java
public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

Bellek dostu bir yaklaşım sergilemek adına, gerçek nesnenin yaratılışını display metodunun ilk çağrılışına kadar erteleyen vekil sınıfımı yazdım. Bu sayede, istemci bir görsel objesi oluştursa bile, o görsel ekrana çizilene kadar sistem belleğinde büyük bir alan işgal etmesini engellemiş oluyorum.

```java
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("yuksek_cozunurluklu_fotograf.jpg");
        System.out.println("Vekil nesne oluşturuldu ancak asıl dosya henüz disken yüklenmedi.");

        image.display(); 
        System.out.println("---");
        image.display(); 
    }
}
```

İstemci kodumda doğrudan RealImage yerine ProxyImage nesnesini çağırarak, ağır dosyanın ilk etapta sadece adıyla sisteme dahil edilmesini sağladım. Gördüğünüz gibi, display metodunu ilk çağırdığımda asıl nesnem belleğe yüklenip çalışıyor, ikinci çağrımda ise yükleme maliyetine tekrar katlanmadan doğrudan gösterim yapıyor.

### Proxy Tasarım Şablonunun AOP ile Olan İlişkisi Nedir?

Aspect-Oriented Programming (AOP) yaklaşımının kalbinde aslında doğrudan Proxy tasarım şablonu yatar; çünkü AOP, loglama, güvenlik veya işlem yönetimi gibi enine kesen özellikleri ana iş mantığımdan ayırmak için arka planda sürekli dinamik vekiller üretir. Ben yazdığım bir metoda özel bir notasyon (örneğin @Transactional) eklediğimde, kullandığım framework gizlice asıl nesnemi sarmalayan bir Proxy nesnesini araya sokarak, orijinal koduma hiç dokunmadan o metodun çağrılmasından hemen önce veya sonra ekstra işlemler çalıştırır. Kısacası Proxy şablonu, AOP'nin o büyülü ve pratik yapısının teknik altyapısını oluşturarak sınıflarımın kirlenmesini önler ve mimarimin görünmez bir müdahale katmanıyla çok daha temiz, modüler ve yönetilebilir kalmasını sağlar.