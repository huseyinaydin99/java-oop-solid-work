### 🧅 Çekirdeği özgür bıraktım: Saf Java ile Onion mimarisi iskeletimi inşa ettim

Spring Framework veya Spring Boot içermeyen içermeyen, düz Java ve Maven kullanan Onion Architecture (Soğan Mimarisi) projesini oluşturdum. Klasör adını ve isimlendirme standartlarını doğru şekilde ayarladım.

Projemi, doğrudan dizin olan 058_java-onion-architecture altında çoklu modül (multi-module) bir Maven projesi olarak oluşturdum.

#### Oluşturulan yapı şu şekildedir:

#### domain Modülü: 
Projenin kalbidir. Varlıklar (entities), değer nesneleri (value objects) ve domain interface'leri burada bulunur. Bu modülün hiçbir dış bağımlılığı ve diğer modüllere bağımlılığı yoktur. (Dışarıya kapalı, en içteki katman) Çekirdeği diğer katmanlardan tamamen bağımsız yapmanın ana amacı; dış dünyadaki teknolojik araçlar (framework, veritabanı, arayüz) zamanla değişse bile, uygulamanın kalbi olan iş kurallarının bu değişimlerden etkilenmeden esnek, kalıcı ve tek başına test edilebilir kalmasını sağlamaktır. Peki neden core katmanının yani uygulamanın kalbi olan iş kurallarının dış etmenlerden etkilenip değişmesini istemeyiz? Cevabı; çünkü uygulamanın asıl varoluş sebebi ve ürettiği gerçek değer bu iş kurallarında yatar; sadece kullandığımız bir araç veya teknoloji değişiyor diye, kusursuz çalışan temel mantığın bozulma riskini ve yeniden yazılma maliyetini önlemek isteriz. Onion mimarisinin temel var oluş amacı tam olarak budur; bağımlılıkların yönünü her zaman dıştan içe doğru zorlayarak uygulamanın merkezini teknik detaylardan tamamen izole etmek ve projenin kontrolünü teknolojilere değil, iş kurallarına vermektir.

#### application Modülü: 
Uygulamanın kullanım senaryolarını (use-case'leri) yani iş akışlarını (business rules) içerir. Ayrıca, altyapı katmanının implemente edeceği Port'lar (interface'ler) ve DTO'lar da burada bulunur. Bu katman yalnızca domain modülüne bağımlıdır.

#### infrastructure Modülü: 
Veritabanı bağlantıları (JDBC/Hibernate vb.), dış servis entegrasyonları ve file system gibi altyapısal işlemlerin yapıldığı katmandır. application katmanında tanımlanan Port'ların (interface'lerin) implementasyonlarını içerir. application modülüne bağımlıdır.

#### presentation Modülü: 
Kullanıcı veya dış dünya ile etkileşime girilen katmandır (Console UI, REST API kontrolleri vb.). application (kullanım senaryolarını çağırmak için) ve infrastructure (uygulama başlatılırken bağımlılıkların enjekte edilmesi - Composition Root için) modüllerine bağımlıdır.

Tüm modüller için standart Maven Java dizin yapıları (src/main/java/com/huseyinaydin ve src/test/java/com/huseyinaydin) hazırlandı. 

---

İş kurallarını merkezde tutan örnek bir Banka Hesabı Para Transferi senaryosu inşa ettim. Amacım; kodun sadece çalışması değil, yıllara meydan okuması, esnek kalması ve SOLID prensiplerine harfiyen uymasıydı.

#### İşte inşa ettiğim bu yapının katman katman anatomisi:

1. Çekirdek (Domain Katmanı) En içte, dış dünyadaki hiçbir framework'ten veya teknolojiden haberi olmayan Account (Hesap) varlığımı (entity) oluşturdum. Benim için bir hesap sadece aptal bir veri taşıyıcısı (anemic domain model) olamazdı; kendi kuralları olmalıydı. İçerisine para çekme (withdraw) ve yatırma (deposit) yeteneklerini yerleştirdim. Bakiye yetersiz olduğunda bir veritabanı veya sistem hatası değil, tamamen kendi iş kuralımı temsil eden InsufficientBalanceException fırlatılmasını sağladım. Veriyi koruma altına alıp iş mantığını varlığın kendisine vererek Tek Sorumluluk Prensibi'ni (Single Responsibility) en temelde uyguladım.

2. Uygulama (Application Katmanı) Bu katmanda sistemin ana akışını, yani kullanım senaryosunu (Use Case) kurguladım. Dışarıdan gelecek talepleri taşıması için TransferRequest (DTO) ve sistemin ne yapabileceğini gösteren TransferMoneyUseCase (Input Port) arayüzünü yazdım. Asıl işi yürütecek olan TransferMoneyService sınıfını yazarken, veritabanına nasıl bağlanacağımı bir saniye bile düşünmedim. Sadece veriye ihtiyacım olduğunu belirten AccountRepository adında bir arayüz (Output Port) tanımladım. Servisimin somut bir veritabanı sınıfına değil, tamamen bu arayüze bağlanmasını sağladım. Böylece Bağımlılıkları Tersine Çevirme Prensibi'ni (Dependency Inversion) kusursuzca işleterek kalbimi altyapı detaylarından kopardım.

3. Altyapı (Infrastructure Katmanı) Uygulama katmanının ihtiyaç duyduğu o AccountRepository arayüzünü dış dünyada, yani burada InMemoryAccountRepository sınıfıyla hayata geçirdim (implemente ettim). Şimdilik verileri sadece bellekte tutan (Map ile) bir yapı kurdum. Mimarim şu an o kadar esnek ki; yarın MySQL, PostgreSQL veya MongoDB'ye geçmek istersem merkezdeki hiçbir kodu ellemeyeceğim. Sadece bu katmanda yeni bir repository sınıfı yazıp arayüzü bağlayacağım. İşte Açık-Kapalı Prensibi (Open-Closed) tam olarak böyle hayat buldu; gelişime açık, değişime kapalıyım.

4. Sunum (Presentation Katmanı / Dış Dünya) En dış katmanda ise Main sınıfıyla projenin ayağa kalktığı yeri (Composition Root) yazdım. Mimarimin tüm parçalarını burada birbiriyle tanıştırdım. Altyapıdan gelen somut veritabanı nesnemi, uygulama katmanından gelen servisin içine burada enjekte ettim. Örnek iki hesap oluşturup aralarında transfer işlemini gerçekleştirdim.

Özetle; iş kurallarımı tamamen merkeze, özgür ve güvende olacak şekilde yerleştirdim. Teknolojiler eskiyip değişse de, gereksinimler büyüse de iç içe geçmeden (spagetti olmadan) ayakta kalacak, sürdürülebilir ve test edilebilir bir yazılımın ruhunu kodlara yansıttım. Proje dizininde kodları incelediğinde, karmaşadan uzak, birbirine saygı duyan ve sınırlarını kesin olarak bilen sınıflar göreceksin!