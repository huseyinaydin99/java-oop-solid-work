### Template Method Nedir, Ne Değildir?

#### Nedir?
Bir algoritmanın genel iskeletini (adımlarını ve sırasını) bir üst (soyut) sınıfta belirleyip, bu adımlardan bazılarının veya tamamının içeriklerinin alt sınıflar tarafından doldurulmasına olanak tanıyan davranışsal bir tasarım şablonudur.

#### Ne Değildir?
Çalışma zamanında (runtime) nesnenin tüm davranışını tamamen değiştirdiğimiz Strategy şablonu değildir; Template Method derleme zamanında (compile-time) miras alma (inheritance) yoluyla çalışır ve algoritmanın "sırasını" asla değiştirmenize izin vermez.

#### Neden Var ve Ana Amacı Ne?
Uygulama içinde birbiriyle neredeyse aynı adımlara sahip ama sadece birkaç noktasında farklılaşan süreçler (kod tekrarları) oluştuğunda; bu ortak adımları tek bir çatı altında toplamak için vardır. Ana amacı, Don't Repeat Yourself (DRY) prensibini uygulayarak algoritmanın kontrolünü merkezi bir yere almak ve sistemi yeni eklentilere açarken değişime kapatmaktır (Open/Closed Principle).

#### Neye Çözüm Getirir?
Kopya-yapıştır yapılarak çoğaltılmış, her birinde ufak tefek değişiklikler olan kod bloklarının yarattığı "spagetti kod" ve "bakım cehennemi" problemine çözüm getirir. Temel algoritma akışında bir hata çıktığında veya bir adım eklendiğinde, 10 farklı sınıfı güncellemek yerine tek bir üst sınıfı güncelleme rahatlığı sağlar.

#### Hangi Senaryolarda Kullanılmalıdır?

#### ETL (Extract, Transform, Load) Süreçleri: 
Veriyi bir yerden okuma, dönüştürme ve başka bir yere yazma işlemleri.

#### Raporlama Çıktıları: 
Veritabanından veriyi çekip aynı mantıkla işledikten sonra PDF, Excel veya HTML formatında dışa aktarma süreçleri.

#### Yaşam Döngüsü (Lifecycle) İşlemleri: 
Uygulama başlatılırken veya bir veritabanı bağlantısı açılıp kapatılırken sırasıyla işletilmesi zorunlu olan (setup -> execute -> teardown) kancalı (hook) süreçler.

#### SOLID ve Sürdürülebilir Kod Örneği: ETL Veri İşleme Süreci
Bu örnekte; veriyi alıp, dönüştürüp, bir yere yüklediğimiz standart bir ETL (Extract, Transform, Load) iş akışını kurguluyoruz.

```java
public abstract class EtlProcessTemplate {

    public final void executeProcess() {
        System.out.println("--- İşlem Başlıyor ---");
        String rawData = extractData();
        String transformedData = transformData(rawData);
        loadData(transformedData);
        if (shouldSendNotification()) {
            sendNotification();
        }
        System.out.println("--- İşlem Tamamlandı ---\n");
    }

    protected abstract String extractData();
    protected abstract String transformData(String rawData);
    protected abstract void loadData(String transformedData);

    protected boolean shouldSendNotification() {
        return true; 
    }

    private void sendNotification() {
        System.out.println("Bildirim: ETL süreci başarıyla tamamlandı.");
    }
}
```

Algoritmanın değişmez omurgasını final bir metotla kurup, alt sınıflara sadece değişmesi gereken kısımları dikte ettiğim merkez noktam burası. Böylece ana iş akışının ve sırasının güvenliğini sağlarken, kanca (hook) metotlar yardımıyla alt sınıflara akışın ufak kısımlarına müdahale etme esnekliği tanıyorum.

```java
public class DatabaseToCsvEtl extends EtlProcessTemplate {

    @Override
    protected String extractData() {
        System.out.println("Veri, SQL sorgusu ile ilişkisel veritabanından çekiliyor...");
        return "Ad: Ahmet, Yas: 30";
    }

    @Override
    protected String transformData(String rawData) {
        System.out.println("Veritabanı satırları CSV virgül ayrım formatına dönüştürülüyor...");
        return rawData.replace("Ad: ", "").replace(", Yas: ", ",");
    }

    @Override
    protected void loadData(String transformedData) {
        System.out.println("Dönüştürülen veri 'kullanicilar.csv' dosyasına yazılıyor: " + transformedData);
    }
}
```

Soyut sınıftaki sözleşmeye harfiyen uyarak sadece CSV formatına özgü okuma, dönüştürme ve kaydetme detaylarına odaklanıyorum. Ana döngünün nasıl veya hangi sırayla çalışacağıyla ilgilenmeden, yalnızca kendi uzmanlık alanımı kodlayarak Tek Sorumluluk Prensibi'ni (SRP) kusursuzca işletiyorum.

```java
public class ApiToCloudEtl extends EtlProcessTemplate {

    @Override
    protected String extractData() {
        System.out.println("Veri, REST API üzerinden JSON formatında alınıyor...");
        return "{\"name\": \"Ayşe\", \"age\": 25}";
    }

    @Override
    protected String transformData(String rawData) {
        System.out.println("JSON verisi bulut sistemine uygun şifreli bir formata çevriliyor...");
        return "ENCRYPTED_DATA_HASH_8832";
    }

    @Override
    protected void loadData(String transformedData) {
        System.out.println("Şifrelenmiş veri AWS S3 Bucket'ına yükleniyor: " + transformedData);
    }

    @Override
    protected boolean shouldSendNotification() {
        return false;
    }
}
```

Bulut entegrasyonuna has işlemleri alt sınıfa taşıyarak, temel koda hiç dokunmadan sistemi tamamen yeni bir teknolojiyle genişletebiliyorum (Open/Closed Prensibi). Ayrıca kanca (hook) metodunu burada ezerek, bulut yüklemelerinde bildirim gönderilmesini engelleyip standart algoritma akışını ihtiyacıma göre esnetebiliyorum.

```java
public class Main {
public static void main(String[] args) {
EtlProcessTemplate csvExport = new DatabaseToCsvEtl();
csvExport.executeProcess();

        EtlProcessTemplate cloudBackup = new ApiToCloudEtl();
        cloudBackup.executeProcess();
    }
}
```

İstemci tarafında hangi işlemciyi kullanırsam kullanayım, işlerin her zaman benim üst sınıfta belirlediğim standart sırayla ve hatasız çalışacağından kesinlikle eminim. Karmaşık if-else bloklarına hiç bulaşmadan, sadece enjekte ettiğim nesneyi değiştirerek birbirinden çok farklı iş akışlarını aynı komutla orkestre edebiliyorum.