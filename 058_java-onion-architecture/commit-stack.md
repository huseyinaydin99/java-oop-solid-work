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

