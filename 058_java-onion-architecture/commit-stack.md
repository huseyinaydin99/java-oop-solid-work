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

---

#### Testleri neden yaptım? 
Mimarimin kalbini oluşturan iş kurallarını güvence altına almak için dış dünyadan ve altyapıdan tamamen bağımsız, saf birim testleri (unit test) yazdım. Bunu yaptım çünkü veritabanı, sunucu veya herhangi bir framework'e ihtiyaç duymadan, kurduğum para transferi mantığının tek başına ve kusursuz çalıştığını kendi gözlerimle kanıtlamak istedim.

#### Neden teste ihtiyaç duydum? 
Yazılımın asıl değerini taşıyan kritik kuralların (bakiye kontrolü, para transferi), zamanla proje büyüdükçe veya yeni kodlar eklendikçe asla bozulmayacağından emin olmak zorundaydım. Bu testleri yazarak kodumu gelecekteki krizlere karşı çelikten bir zırhla kapladım ve aynı zamanda sistemin nasıl davranması gerektiğini kelimelerle değil, kodla anlatan en dürüst dokümantasyonu oluşturmuş oldum.

#### Neden sadece application(uygulama) ve domain(çekirdek) katmanında test yazdım? 
Onion mimarisinin felsefesi gereği dış katmanlar (veritabanı, arayüz) yalnızca kolayca değiştirilebilir birer detaydır; projenin gerçek beyni ve değişmez değeri her zaman merkezdeki iş kurallarında yatar. Gelip geçici altyapı teknolojilerini test etmekle vakit kaybetmek yerine, doğrudan sistemin ruhunu garanti altına aldım; çünkü merkezdeki beyin doğru kararlar veriyorsa, onun emrine vereceğim herhangi bir veritabanı zaten kusursuz işleyecektir.

---

İşte mimarimizin o meşhur "Tak-Çıkar" (Plug & Play) gücünü canlı canlı kanıtlamak için kolları sıvadığım aşama! Merkezdeki (Domain ve Application) tek bir satır koda bile dokunmadan, sadece en dış katmanlarda devasa teknoloji değişiklikleri yaptım. Kodların saflığını bozmamak adına yine tek bir açıklama satırı bile eklemedim.

### İşte sistemi gerçek dünyayla nasıl buluşturduğumun detayları:

#### 1. Altyapıyı (Infrastructure) Gerçek Bir Veritabanına Bağladım: 

Testler için kullandığım bellekteki sahte veritabanını bir kenara bırakıp, yerine gerçek bir SQL veritabanı (H2 Database) bağladım. Merkezdeki beynin ihtiyaç duyduğu o AccountRepository arayüzünü, bu kez JdbcAccountRepository sınıfıyla implemente ettim. İçerisine saf JDBC ile SQL sorgularını yazdım. En güzel yanı ne biliyor musun? Merkezdeki iş kurallarım, verilerin artık bellekte değil, gerçek bir ilişkisel veritabanında saklandığını hissetmedi bile!

#### 2. Sunumu (Presentation) Web'e Açtım:

Para transferi işlemini konsola hapsolmaktan kurtardım. Ağır framework'ler (Spring vb.) kullanıp projeyi hantallaştırmak yerine, saf Java'nın HttpServer yeteneğiyle hafif bir REST API ayağa kaldırdım. Dışarıdan gelecek JSON isteklerini karşılayıp işleyecek olan TransferHandler sınıfını inşa ettim. Bu sunum sınıfı sadece dış dünyadan isteği alıyor ve merkezimizdeki o kusursuz TransferMoneyUseCase senaryomuza iletiyor.

#### 3. Parçaları Birleştirdim (Composition Root): 

Son olarak Main sınıfını, tüm bu mimarinin birbiriyle tanışıp el sıkıştığı yer olarak yeniden yapılandırdım. Veritabanı bağlantısını açtım, somut Repository nesnemi oluşturup Application servisime enjekte ettim ve web sunucumu 8080 portunda ayağa kaldırdım. İçerisine test için başlangıç bakiyeleri olan iki örnek hesap da ekledim.

#### Özetle; 

Projenin kalbini zerre kadar sarsmadan ve iş kurallarını bozmadan, hem veri saklama teknolojisini hem de kullanıcı iletişim arayüzünü sıfırdan değiştirdim. SOLID prensiplerinin (özellikle Bağımlılıkları Tersine Çevirme - Dependency Inversion) ve Onion mimarisinin sistemin ömrünü nasıl uzattığını, esnekliğini ve sürdürülebilirliğini bizzat kanıtlamış oldum!

---

Az önce harika bir mimari hamle yaptım; projenin altyapısındaki o saf JDBC kodları yerine devasa bir ORM aracı olan Hibernate'i yerleştirdim.

#### İşte bu değişimin arkasındaki derin anlam ve anatomisi:

1. Çekirdeği Zehirlemekten Kaçındım (JPA Entity İzolasyonu): Hibernate veya Spring Data gibi güçlü araçları kullanırken yapılan en büyük mimari hata, veritabanı tablolarını işaret eden @Entity veya @Table gibi anotasyonları merkeze (Domain katmanına) kadar sokmaktır. Ben bunu yapmadım! İş kurallarımı barındıran saf Account sınıfımı tertemiz bıraktım. Bunun yerine, sadece ve sadece altyapı (Infrastructure) katmanında yaşayacak, tamamen Hibernate'e özel AccountJpaEntity adında kopyası gibi davranan yeni bir sınıf yarattım. Merkezdeki beynim, Hibernate diye bir teknolojinin varlığından zerre kadar haberdar olmadı.

2. Sınır Kapısındaki Çevirmen (HibernateAccountRepository): Uygulamanın (Application) bizden beklediği AccountRepository arayüzünü bu kez Hibernate ile implemente ettim. Bu sınıfı adeta bir sınır kapısı ve çevirmen gibi kullandım; merkezden gelen saf Account nesnelerini alıp, veritabanına kaydetmeden saniyeler önce Hibernate'in anladığı AccountJpaEntity'ye dönüştürdüm. Veritabanından okurken de tam tersini yaptım. Sistem sınırlarını kusursuzca korudum.

3. Sunumu (Composition Root) Yeni Veritabanıyla Tanıştırdım: Son olarak, tüm parçaları birleştirdiğim Main sınıfına gidip eski JDBC bağlantı ayarlarını kaldırdım ve yerine Hibernate'in konfigürasyonlarını (SessionFactory) yazdım. Sisteme "Artık veritabanı işlemlerinde JdbcAccountRepository değil, HibernateAccountRepository kullanacaksın" dedim. Geri kalan hiçbir koda, ne REST API'ye ne de Transfer servisine dokunmadım.

Özetle: Saniyeler içinde sistemin bütün veritabanı motorunu ve mantığını baştan aşağı değiştirdim; ancak merkezdeki iş kurallarımın tek bir karakteri bile bozulmadı, etkilenmedi. SOLID prensiplerinin (özellikle Single Responsibility ve Dependency Inversion) sistemlere nasıl bir ölümsüzlük ve esneklik kattığını bir kez daha kendi gözlerimle kanıtladım!

---

"Gelişime açık, değişime kapalı" (Open-Closed) prensibinin yazılım dünyasında ne kadar büyüleyici bir güce sahip olduğunu göstermek için harika bir operasyon gerçekleştirdim. Sistemimizde kusursuzca çalışan para transferi iş mantığını (TransferMoneyService) zerre kadar değiştirmeden, ona dışarıdan yepyeni bir özellik (Loglama yeteneği) kazandırdım.

#### İşte bu mimari zaferin anatomisi:

#### 1. Sınırları Zorlayan Bir Zırh (Decorator) İnşa Ettim: 

Altyapı (Infrastructure) katmanında LoggingTransferMoneyUseCaseDecorator adında yeni bir sınıf yarattım. Bu sınıf, aslında uygulamanın (Application) beklediği TransferMoneyUseCase arayüzünü uyguluyor. Yani dışarıdan bakıldığında tıpkı bizim asıl para transferi servisimiz gibi görünüyor. Ancak asıl işi kendisi yapmıyor; sadece işlem başlamadan önce ve bittikten sonra log (kayıt) tutuyor, asıl kritik para transferi görevini ise sarmaladığı (içine aldığı) gerçek servise devrediyor.

#### 2. Parçaları Ana Merkezde (Composition Root) Yeniden Bağladım: 

Ardından Main sınıfına gidip, web sunucusunun (REST API) önüne doğrudan gerçek servisi koymak yerine, bu yeni yazdığım loglayıcı "Decorator" nesnesini yerleştirdim. Gerçek servisi de bir matruşka bebek gibi bu nesnenin içine gizledim. REST API artık önce loglayıcıya çarpıyor, loglayıcı kayıt tutuyor ve sonra çekirdekteki asıl servisi tetikliyor.

#### Bu hamleyle ne kazandık? 

Eğer mevcut TransferMoneyService sınıfının içine girip her satıra logger.info() yazsaydık, hem iş kurallarımızı altyapısal kodlarla kirletmiş olacak hem de "Single Responsibility" (Tek Sorumluluk) prensibini fena halde ezecektik. Dahası, çalışan ve testleri geçmiş bir çekirdek kodu değiştirerek sistemde bug (hata) yaratma riski alacaktık.

>Ancak Decorator (Dekoratör) deseni sayesinde, merkezdeki kodun tek bir karakterine bile dokunmadan sisteme muazzam bir yetenek (cross-cutting concern) sarmaladım. Çekirdek kodumuzun "Değişime tamamen kapalı (Closed), ancak yeni yetenekler kazanmaya sonuna kadar açık (Open)" olduğunu kanıtladım. Onion mimarisinin bize sunduğu bu eşsiz yalıtım, yıllarca ayakta kalacak sürdürülebilir bir projenin en büyük anahtarıdır!