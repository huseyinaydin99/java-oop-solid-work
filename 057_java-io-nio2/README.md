# 055 - Java I/O ve NIO.2: Log Analiz Aracı

## Bu modülü neden yazdım

Hangi projeye girsem bir yerde dosya okuyup yazan bir kod çıkıyor karşıma. Bir CSV içe aktarılıyor, bir rapor dışa veriliyor, gece çalışan bir iş log dosyalarını topluyor. Ve bu kodlar, projenin en az özen gösterilen yerleri oluyor genelde. "Dosyayı oku, satırları dön, bitti" diye yazılıp geçiliyor. Sonra bir gün Windows sunucuda Türkçe karakterler soru işaretine dönüyor, bir gün 2 GB'lık bir log yüzünden uygulama `OutOfMemoryError` veriyor, bir gün de "too many open files" hatasıyla servis duruyor. Üçünün de kökü aynı: dosya işlemlerini hafife almak.

Bu modülü, bu konuları kendim için bir kere düzgünce oturtmak amacıyla yazdım. Kuru örnekler yerine gerçek bir işe benzesin istedim: bir klasördeki uygulama loglarını okuyan, analiz eden, rapor yazan ve işlediği dosyaları arşive kaldıran küçük bir araç. Konuları bu aracın içine yerleştirdim, aracın kendisinin de temiz bir tasarımı olsun diye uğraştım.

## java.io ile java.nio.file arasındaki fark

`java.io` Java'nın ilk günlerinden kalma. Merkezinde `File` sınıfı var ve bu sınıf aslında sadece bir yol bilgisi tutuyor; ama üzerinde `delete()` gibi metotlar var ve bunlar başarısız olunca sadece `false` dönüyor. Neden silinemediğini söylemiyor. Okumak için de `FileReader`'ı `BufferedReader`'a sarıyorsun, katman katman.

`java.nio.file` (NIO.2) Java 7 ile geldi ve bu dağınıklığı toparladı. İşi iki parçaya ayırdı:

- `Path` sadece bir adres. Oluşturmak, birleştirmek, sadeleştirmek diske dokunmaz.
- `Files` ise asıl işi yapan yardımcı sınıf: okuma, yazma, kopyalama, taşıma, silme, listeleme.

Bence en önemli fark hata mesajlarında. `Files.delete` başarısız olursa `NoSuchFileException`, `DirectoryNotEmptyException` gibi neyin yanlış gittiğini söyleyen bir exception fırlatıyor. Gece üçte log okurken bu fark çok şey değiştiriyor.

Modülde aynı dosyayı iki yolla da okuyan küçük bir karşılaştırma var (`ClassicVsNioReadDemo`). İkisi de aynı sonucu veriyor; fark, kodun ne kadar niyetini açık söylediğinde.

## try-with-resources neden bu kadar önemli

Bir dosyayı açtığında işletim sistemi sana bir tanıtıcı (file handle) veriyor ve bu tanıtıcıların sayısı sınırlı. Kapatmayı unutursan hiçbir şey olmuyor; ta ki bir gün sınır dolana kadar. Kaynak sızıntısının asıl tehlikesi bu sessizlik. Test ortamında hiç görünmüyor, canlıda haftalar sonra patlıyor ve sebebini bulmak saatler sürüyor.

try-with-resources bu derdi dile bırakıyor. Blok nasıl biterse bitsin, normal ya da exception ile, kaynak kapanıyor. Modülde üç şeyi özellikle göstermek istedim:

- **Kapanma sırası:** Birden fazla kaynak açınca açılışın tersi sırayla kapanıyorlar. Mantığı şu: sonra açılan kaynak genelde öncekine bağımlıdır, önce onun kapanması gerekir.
- **Kendi AutoCloseable sınıfım:** `TrackedResource` açılıp kapandığını haber veren küçük bir sınıf. Kapanma sırasını gözle görmek için yazdım. `TemporaryWorkspace` ise işe yarayan bir örnek: demo bittiğinde geçici klasörü siliyor.
- **Suppressed exception:** Blok içinde bir hata olur, kapanırken de ikinci bir hata olursa asıl hata kaybolmuyor; kapanış hatası ona "suppressed" olarak ekleniyor. Klasik `try-finally` ile yazınca kapanış hatası asıl hatayı eziyordu ve kimse gerçek sorunu göremiyordu.

## Uygulamanın akışı

```
bulma → okuma → ayrıştırma → analiz → rapor → arşiv
```

1. **Bulma** (`ExtensionLogFileFinder`): Klasördeki `.log` uzantılı dosyaları `Files.list` ile buluyorum. `walk` kullanmadım, çünkü arşiv klasörü logların altında duruyor ve arşivlenmiş dosyaların tekrar işlenmesini istemiyorum.
2. **Okuma** (`StreamingLogFileReader`): `Files.lines` ile satır satır, tembel bir stream olarak.
3. **Ayrıştırma** (`PipeDelimitedLogLineParser`): `2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı` satırını bir `LogEntry` record'una çeviriyor. Bozuk satırda exception fırlatmıyor, boş `Optional` dönüyor; çünkü bu senaryoda bozuk satır beklenen bir durum.
4. **Analiz** (`StatisticsLogAnalyzer`): Seviyelere göre sayım, bozuk satır sayısı, en çok hata veren servis, ilk ve son hata zamanı. Satırlar tek tek aktığı için hiçbir listeyi bellekte tutmuyor.
5. **Rapor** (`FileReportWriter`): Sonucu hem TXT hem CSV olarak yazıyor.
6. **Arşiv** (`TimestampedLogArchiver`): İşlenen dosyayı `archive/payment-service_20261027-143210.log` gibi tarih damgalı bir adla taşıyor.

Bu adımları sıralayan tek sınıf `LogAnalysisPipeline`. Kendisi hiçbir iş yapmıyor, sadece hangi adımın ne zaman çalışacağını biliyor. Arşivi bilerek en sona koydum: rapor yazılırken bir şey ters giderse loglar yerinde kalıyor ve iş tekrar denenebiliyor.

## SOLID'i nerede uyguladım

- **Tek sorumluluk (SRP):** Dosya bulma, okuma, ayrıştırma, analiz, rapor biçimlendirme, rapor yazma ve arşivleme ayrı sınıflarda. Mesela `FileReportWriter` raporun neye benzediğini bilmiyor, sadece satırları dosyaya yazmayı biliyor. Biçim `ReportFormatter`'ın işi.
- **Açık/kapalı (OCP):** Yeni bir rapor formatı eklemek için `ReportFormatter`'ı uygulayan yeni bir sınıf yazıp `Main`'de bir satır eklemek yetiyor. JSON rapor istesem mevcut hiçbir sınıfa dokunmam gerekmiyor. Demo adımları da aynı mantıkla `DemoStep` arayüzü üzerinden çalışıyor.
- **Liskov (LSP):** `LogFileFinder`, `LogArchiver` gibi arayüzlerin her uygulaması pipeline'da birbirinin yerine sorunsuz geçebiliyor; pipeline hangi uygulamayla çalıştığını bilmiyor.
- **Arayüz ayrımı (ISP):** Arayüzler küçük, çoğu tek metotlu. `PipelineListener`'da metotlar `default` olduğu için dinleyen taraf sadece ilgilendiği olayı uyguluyor.
- **Bağımlılığın tersine çevrilmesi (DIP):** Pipeline somut sınıfları değil arayüzleri tanıyor. Bütün bağımlılıklar constructor'dan geliyor; nesnelerin `new` ile yaratıldığı tek yer `Main`. Arşivleyici bile zamanı `Clock` olarak dışarıdan alıyor, bu sayede testte sabit bir zaman verip dosya adını kesin olarak doğrulayabiliyorum.

Bunların yanında `LogLevel` için enum, `LogEntry`, `AnalysisResult` ve `ErrorSummary` için record kullandım. Checked `IOException`'ları `LogReadException`, `ReportWriteException`, `ArchiveException` gibi ne olduğunu söyleyen exception'lara sardım ve asıl sebebi `cause` olarak hep taşıdım. Hiçbir yerde hatayı yutmuyorum.

## Dikkat edilmesi gerekenler

**Kodlama.** Java 18'den beri varsayılan kodlama UTF-8, ama bu "artık düşünmeme gerek yok" demek değil. Dosya başka bir sistemden gelebilir, kod eski bir JVM'de çalışabilir. Ben her okuma ve yazmada `StandardCharsets.UTF_8`'i açıkça yazıyorum ki varsayım kodun içinde görünsün. Demoda aynı Türkçe satırı ISO-8859-1 ile okuyup her Türkçe harfin yerine iki anlamsız karakter çıktığını ("Ödeme" yerine "Ã" ile başlayan bir şey) bilerek gösteriyorum. Bir de daha sinsi bir tuzak var: Türkçe yerel ayarda `"info".toUpperCase()` sonucu `"İNFO"` oluyor ve enum eşleşmesi bozuluyor. O yüzden `LogLevel.fromText` içinde `Locale.ROOT` kullanıyorum.

**Büyük dosyalar.** `Files.readAllLines` pratik ama bütün dosyayı bir listeye dolduruyor. Birkaç yüz satır için sorun değil, birkaç gigabaytlık log için uygulamayı düşürür. `Files.lines` satırları ihtiyaç oldukça okuyor; bellekte aynı anda sadece tampon kadar veri oluyor. Demoda 200 bin satırlık bir dosyayı bu şekilde işliyorum.

**Kapatılmayan stream'ler.** `Files.lines`, `Files.list` ve `Files.walk` sıradan stream'lere benziyor ama arkalarında açık bir dosya ya da dizin tanıtıcısı var. try-with-resources içinde açılmazlarsa tanıtıcı sızıyor. Pipeline'da birden fazla dosyayı `flatMap` ile tek akışa bağladım; `flatMap` her dosyanın stream'ini içeriği bitince kendisi kapatıyor, dıştaki try-with-resources da işin yarıda kesilmesi ihtimaline karşı açık kalanı kapatıyor.

**Taşıma.** `Files.move` için `ATOMIC_MOVE` istiyorum, çünkü atomik taşımada dosya ya tamamen eski yerinde ya tamamen yeni yerinde oluyor, yarım kopya kalmıyor. Ama farklı diskler arasında atomik taşıma mümkün değil; o durumda `AtomicMoveNotSupportedException` yakalayıp `REPLACE_EXISTING` ile normal taşımaya geçiyorum.

**Açma seçenekleri.** Raporu `CREATE, TRUNCATE_EXISTING` ile yazıyorum, çünkü her çalıştırmada baştan üretilmesi gerekiyor. Geçmiş kaydı gibi biriken dosyalarda ise `CREATE, APPEND` doğru seçim. `CREATE_NEW` de başka birinin dosyasını ezmemek için var: dosya zaten varsa hata veriyor. Demoda dördünü de aynı dosya üzerinde sırayla gösteriyorum.

## Çalıştırma

Java 21 ve Maven gerekiyor.

```bash
# Testler
mvn test

# Demo
mvn compile exec:java

# Ya da jar ile
mvn package
java -jar target/java-io-nio2-1.0.0.jar
```

Demo kendi örnek log dosyalarını sistemin geçici dizininde açtığı bir klasöre üretiyor, her adımı konsola yazıyor ve bitince klasörü siliyor. Bilgisayarda kalıcı bir dosya bırakmıyor. Windows terminalinde Türkçe karakterler bozuk görünürse sorun koddan değil konsolun kodlamasından; `chcp 65001` ile düzeliyor.

Testler JUnit 5'in `@TempDir` özelliğiyle gerçek dosya sistemi üzerinde çalışıyor; sahte (mock) bir dosya sistemi kullanmadım, çünkü bu modülde test etmek istediğim şey zaten dosya sisteminin kendisiyle olan etkileşim.

## Klasör yapısı

```
055_java-io-nio2
├── pom.xml
└── src
    ├── main/java/tr/com/huseyinaydin
    │   ├── Main.java                 # giriş noktası, bileşenlerin bağlandığı yer
    │   ├── loganalyzer
    │   │   ├── domain                # LogLevel, LogEntry, AnalysisResult, ErrorSummary, LogLineFormat
    │   │   ├── discovery             # log dosyalarını bulma
    │   │   ├── reading               # Files.lines ile okuma
    │   │   ├── parsing               # satır ayrıştırma
    │   │   ├── analysis              # istatistik çıkarma
    │   │   ├── report                # TXT/CSV biçimlendirme ve yazma
    │   │   ├── archive               # tarih damgalı arşivleme
    │   │   ├── pipeline              # akışın yönetimi
    │   │   └── exception             # anlamlı özel exception'lar
    │   ├── resource                  # TrackedResource (AutoCloseable örneği)
    │   ├── support                   # TemporaryWorkspace
    │   └── demo                      # konu konu konsol demoları
    └── test/java/tr/com/huseyinaydin # @TempDir ile dosya sistemi testleri
```
