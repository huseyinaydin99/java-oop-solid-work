#### 🍃 **Spring Boot MongoDB CRUD ve Kullanılan Anotasyonlar**

---

#### 🌿 **Genel Bakış**

Bu yapı, Spring Boot’un MongoDB ile tam entegrasyonlu bir CRUD (Create, Read, Update, Delete) uygulamasıdır.
Her katman — **Model**, **Repository**, **Service** ve **Controller** — tek bir amaca hizmet eder:
Verinin MongoDB’de doğru şekilde saklanması, geri çağrılması, güncellenmesi ve silinmesini garanti etmek.
Kodun yapısı hem temiz mimariyi hem de katmanlı düşünceyi temsil eder.
MongoDB’nin NoSQL doğası sayesinde tablo bağımlılığı olmadan esnek bir veri yönetimi sağlanır. 📦

---

#### 🧩 **@Document ve @Id – MongoDB’de Veri Temsili**

`@Document(collection = "tutorials")` anotasyonu, bu sınıfın MongoDB’de “tutorials” adlı koleksiyonla eşleştiğini belirtir.
Yani SQL dünyasındaki tablo karşılığı, NoSQL’de bir koleksiyondur.
Her `Tutorial` nesnesi, bu koleksiyonda bir belge (document) olarak saklanır.
`@Id` anotasyonu, MongoDB’de her kaydın benzersiz kimliğini (ObjectId) temsil eder.
Bu kimlik veritabanı tarafından otomatik oluşturulur ve 12 baytlık hexadecimal bir dizidir.
Bu dizide oluşturulma zamanı, makine bilgisi ve artan sayaç bilgisi saklanır — bu sayede benzersizdir. 🧠
Kısaca: `id` alanı benim elle atadığım bir değer değildir, MongoDB’nin sistematik zekâsı tarafından yaratılır.

---

#### 🪶 **Lombok Anotasyonları (@Data, @AllArgsConstructor, @NoArgsConstructor, @ToString)**

Kodun en sade ama etkili kısmı Lombok’tur.
`@Data` anotasyonu, `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode` gibi metotları tek kalemde üretir.
`@AllArgsConstructor` ve `@NoArgsConstructor` ise sınıfa tam parametreli ve parametresiz kurucu metot kazandırır.
Bu sayede model sınıfı yalın, okunaklı ve bakımı kolay hale gelir. ✨
Lombok, Java dünyasında kod tekrarını azaltarak geliştiriciye zaman kazandırır.

---

#### 🧱 **Repository Katmanı: MongoRepository ile Veri Erişimi**

`public interface TutorialRepository extends MongoRepository<Tutorial, String>`
Bu satır, veri erişim katmanının temelini oluşturur.
Spring Data MongoDB, `MongoRepository` arayüzünü kullanarak CRUD işlemlerini otomatik hale getirir.
Yani `findAll()`, `save()`, `deleteById()` gibi metotları manuel yazmam gerekmez.
Ayrıca `findByPublished(boolean published)` ve `findByTitleContaining(String title)` gibi **custom query** metotları tanımlayabilirim.
Bu metotlar, method name conventions denilen güçlü bir Spring özelliğidir — sorguların ad üzerinden oluşturulmasını sağlar.
📘 **Farkı:**
`JpaRepository` SQL tabanlıdır, tablolarla ve ilişkilerle çalışır.
`MongoRepository` ise belge (document) yapısını kullanır ve ilişkisel olmayan bir yapıda çalışır.
Yani veri bir tabloya değil, JSON benzeri dokümanlara kaydedilir.

---

#### ⚙️ **Service Katmanı – İş Mantığının Kalbi**

`@Service` anotasyonu, bu sınıfın iş mantığını barındırdığını belirtir.
Uygulamanın veri yönetimi, hata yakalama, durum kodu belirleme ve işlem sırasını burada yönetirim.
`@Transactional` anotasyonu, veritabanı işlemlerinin tek bir bütün olarak yürütülmesini sağlar.
Herhangi bir aşamada hata olursa işlem geri alınır (rollback).
MongoDB transaction desteği sınırlıdır, ama tek koleksiyon işlemlerinde tutarlılık sağlar.

#### 🧠 **CRUD İşlemlerinin Özeti:**

* **getAllTutorials(String title)** → Tüm verileri veya başlığa göre filtrelenmiş kayıtları döndürür.
* **getTutorialById(String id)** → Belirli bir ID’ye sahip veriyi getirir.
* **findByPublished(boolean flag)** → Yayınlanmış veya taslak durumundaki kayıtları döndürür.
* **createTutorial(Tutorial tutorial)** → Yeni bir belge ekler.
* **updateTutorial(String id, Tutorial tutorial)** → Var olan belgeyi günceller.
* **deleteTutorial(String id)** → Belirli belgeyi siler.
* **deleteAllTutorials()** → Koleksiyondaki tüm belgeleri siler.

Her işlem `ResponseEntity` döndürür. Bu, hem veriyi hem de HTTP durum kodunu birlikte taşıyan zarif bir yapıdır.
Bu sayede istemci tarafı her zaman anlamlı bir geri dönüş alır. 💬

---

#### 🌐 **Controller Katmanı – API Giriş Noktası**

`@RestController` anotasyonu, sınıfın REST API uç noktalarını yönettiğini belirtir.
`@RequestMapping("/api")` ile tüm URL’lerin ortak kök yolu tanımlanır.
`@CrossOrigin(origins = "*")` sayesinde farklı domainlerden gelen istekler kabul edilir — bu, özellikle frontend (React, Angular) tarafı için önemlidir.

#### 📡 **Temel Endpoint’ler:**

* `GET /api/tutorials` → Tüm kayıtları listeler.
* `GET /api/tutorials/{id}` → ID’ye göre getirir.
* `GET /api/tutorials/published` → Sadece yayımlanmış olanları listeler.
* `POST /api/tutorials` → Yeni kayıt ekler.
* `PUT /api/tutorials/{id}` → Mevcut kaydı günceller.
* `DELETE /api/tutorials/{id}` → Belirli kaydı siler.
* `DELETE /api/tutorials` → Tüm kayıtları siler.

`@PathVariable`, URL içinden parametre almayı;
`@RequestParam`, query parametreleri yönetmeyi;
`@RequestBody`, JSON veriyi nesneye dönüştürmeyi sağlar.
Bu yapı, REST mimarisinin en saf hâlidir: basit, anlaşılır, kaynak odaklı. 🔗

---

#### 🧮 **Spring Data JPA vs Spring Data MongoDB**

| Özellik         | Spring Data JPA           | Spring Data MongoDB              |
| --------------- | ------------------------- | -------------------------------- |
| Veri Modeli     | İlişkisel (tablo, sütun)  | Belge tabanlı (JSON)             |
| Sorgu Dili      | JPQL / SQL                | BSON / Query API                 |
| Transaction     | Gelişmiş                  | Sınırlı (koleksiyon bazlı)       |
| Performans      | Karmaşık sorgularda güçlü | Büyük veri ve esneklikte güçlü   |
| İlişki Yönetimi | OneToMany, ManyToOne      | Gömülü doküman veya referans ile |
| Repository      | JpaRepository             | MongoRepository                  |

MongoDB, veri yapısının dinamik olduğu, tablo ilişkilerinin olmadığı durumlarda esneklik sağlar.
Veri, JSON benzeri BSON formatında tutulur ve schema zorunluluğu yoktur. 🌱

---

#### 🧭 **Uygulama Akış Şeması**

```
             🧑‍💻 İstek (Request)
                     │
                     ▼
          🌐 TutorialController
                     │
                     ▼
           ⚙️ TutorialService
                     │
                     ▼
        🧱 TutorialRepository (MongoRepository)
                     │
                     ▼
          🍃 MongoDB Koleksiyonu (tutorials)
                     │
                     ▼
            ✅ JSON Formatında Yanıt
```

---

#### 🧠 **Sonuç**

Bu proje, Spring Boot’un soyutlama gücüyle MongoDB’nin esnekliğini birleştirir.
Repository katmanı kod yazmadan sorgu yapmayı, Service katmanı veri bütünlüğünü, Controller katmanı ise REST mimarisinin sadeliğini sağlar.
MongoDB’nin belge tabanlı yapısı, hız ve ölçeklenebilirlik sunarken; Spring’in anotasyon temelli yaklaşımı uygulamayı temiz, okunabilir ve yönetilebilir hale getirir.
Sonuçta ortaya çıkan şey yalnızca bir CRUD uygulaması değil — iyi tasarlanmış, genişlemeye açık bir veri sistemi mimarisidir. ⚙️🌿

---

---

#### 🍃 **MongoDB Nedir, Ne Değildir ve SQL Veritabanlarından Farkı**

---

#### 🌿 **MongoDB Nedir?**

MongoDB, **NoSQL** (Not Only SQL) paradigmasına dayanan, belge (document) tabanlı, açık kaynak kodlu bir veritabanıdır.
Veriler, JSON’a çok benzeyen **BSON** (Binary JSON) formatında tutulur. Bu sayede veri yapısı esnek, dinamik ve ilişkisel veritabanlarına göre çok daha serbesttir.
Klasik anlamda bir tablo, satır veya kolon zorunluluğu yoktur; her belge (document), kendi yapısına sahip olabilir.
Bu, farklı veri türlerini aynı koleksiyonda saklamayı mümkün kılar.
MongoDB’nin temel amacı, **yapısal olmayan büyük veriyi** hızlı, ölçeklenebilir ve dağıtık biçimde yönetmektir. 🚀

Kısacası MongoDB, “önce veri gelsin, şemayı sonra düşünürüz” mantığıyla hareket eder.
Modern web uygulamalarının hızla değişen veri ihtiyaçlarına cevap verebilmek için doğmuştur. ⚡

---

#### 🚫 **Ne Değildir?**

MongoDB bir SQL veritabanı değildir; yani tablolar, foreign key’ler, join işlemleri ve sabit şemalar üzerine kurulu değildir.
Veri bütünlüğü, transaction kuralları veya ilişkisel yapılar üzerine sıkı kontrol sağlamaz.
Bu onun zayıf yönü değil, **bilinçli bir tercihtir** — çünkü amaç esneklik ve hızdır, katılık değil.
Yani MongoDB, bankacılık sistemlerinde olduğu gibi sıkı tutarlılık isteyen senaryolarda değil; dinamik, değişken, büyük hacimli verilerde parlayan bir çözümdür. 🌪️

---

#### ⚙️ **Neden Vardır?**

MongoDB’nin varlık nedeni, modern dünyanın veri problemini çözmektir:
Artık veriler yalnızca sayılar veya kısa metinler değildir; karmaşık, değişken, hızlı akan yapılardır.
Sosyal medya, IoT cihazları, sensör verileri, log kayıtları, dinamik içerik sistemleri — bunların hepsi yapısal olmayan devasa veri yığınları üretir.
MongoDB, bu verileri **ölçeklenebilir**, **schema-less** ve **yüksek performanslı** şekilde saklamak için geliştirilmiştir. 🌍

SQL sistemleri sabit şema ister: tablo yapısı değişirse migration gerekir.
MongoDB ise her belgeye özel alanlar eklemeye izin verir; schema değişmeden genişler.
Bu da geliştirme sürecinde büyük bir esneklik sağlar. 💡

---

⚙️ MongoDB Bellek Kullanımı: Hız İçin RAM’e Dayalı Performans Stratejisi 💾⚡

MongoDB bellek kullanımında oldukça agresif bir önbellekleme stratejisi izler.
Sık erişilen veriler RAM’de tutulur, böylece disk erişimi minimuma iner ve okuma hızları ciddi oranda artar. ⚡
Bellek kullanımı dinamik olarak ölçeklenir; sistemde ne kadar RAM varsa MongoDB onu verimli biçimde kullanmaya çalışır.
Ancak bu durum, sınırlı bellekli ortamlarda sistemin kaynakları zorlamasına neden olabilir; bu yüzden yapılandırma dikkatle yapılmalıdır. 🧠
MongoDB genellikle fazla bellek kullanır, çünkü performans için veriyi olabildiğince RAM’de tutarak disk erişimini en aza indirmeyi hedefler. ⚡

---

#### 🧱 **SQL ve MongoDB Arasındaki Farklılıklar**

| Özellik                 | SQL Veritabanı                           | MongoDB                                               |
| ----------------------- | ---------------------------------------- | ----------------------------------------------------- |
| **Veri Modeli**         | Tablo-satır-sütun yapısı                 | Koleksiyon-belge yapısı                               |
| **Şema (Schema)**       | Katı ve önceden tanımlı                  | Esnek, dinamik, belge bazlı                           |
| **İlişkiler**           | Foreign key, join’ler ile sağlanır       | Gömülü (embedded) veya referans dokümanlarla sağlanır |
| **Sorgu Dili**          | SQL (Structured Query Language)          | BSON Query Language                                   |
| **Transaction**         | Gelişmiş, ACID odaklı                    | Koleksiyon bazlı sınırlı transaction desteği          |
| **Ölçeklenebilirlik**   | Dikey (donanım artırarak)                | Yatay (node ekleyerek)                                |
| **Performans**          | Küçük ama tutarlı veri setlerinde etkili | Büyük, dağınık veri setlerinde üstün                  |
| **Veri Saklama Biçimi** | Satır temelli                            | JSON benzeri belge temelli                            |
| **Kullanım Alanı**      | ERP, muhasebe, finans, e-ticaret         | Sosyal medya, analitik, IoT, büyük veri sistemleri    |

SQL sistemleri düzeni ve bütünlüğü sever; MongoDB ise özgürlüğü ve hızı.
SQL, verinin güvenilirliğini merkezine alır. MongoDB ise **ölçeklenebilirliği ve esnekliği** önceler.

---

#### ⚖️ **Hangi Durumda SQL, Hangi Durumda MongoDB?**

#### ✅ **SQL Tercih Edilmelidir:**

* Verinin yapısı katıdır, alanlar değişmez.
* Transaction (işlem bütünlüğü) kritik öneme sahiptir.
* Veri tutarlılığı ve referanslar ön plandadır.
* Finans, muhasebe, insan kaynakları gibi sistemlerde kesin doğruluk gerekir.
* Karmaşık sorgular ve JOIN işlemleri yoğun olarak kullanılır.

**Örnek:** Banka sistemleri, ERP çözümleri, rezervasyon sistemleri. 🏦

---

#### 🌱 **MongoDB Tercih Edilmelidir:**

* Verinin yapısı dinamik, değişken veya öngörülemezdir.
* Büyük veri (Big Data) veya hızlı akış (streaming data) yönetimi gereklidir.
* Geliştirme hızı, schema değişikliğinden daha değerlidir.
* Uygulama sürekli evrim geçiriyorsa (örneğin mikroservis yapısında).
* API tabanlı, JSON odaklı sistemlerde hızlı entegrasyon gerekiyorsa.

**Örnek:** Sosyal medya platformları, log analizi sistemleri, IoT veri merkezleri, içerik yönetim sistemleri (CMS). 🌐

---

#### 🔭 **Kısa Şema Özeti**

```
          🧠 Uygulama Katmanı
                 │
                 ▼
        🌿 MongoDB Koleksiyonu
     (JSON/BSON Belgeler Halinde)
                 │
                 ▼
      ⚡ Hızlı Okuma/Yazma, Esnek Yapı
                 │
                 ▼
      🚀 Dağıtık, Ölçeklenebilir Veri Yönetimi
```

---

#### 💡 **Sonuç**

MongoDB, klasik ilişkisel dünyaya “veri artık sadece satır değildir” diyerek meydan okur.
SQL veritabanları, **disiplin** ve **bütünlük** sunarken; MongoDB, **özgürlük** ve **esneklik** getirir.
Doğru sistem, doğru problem için seçilmelidir.
Finansal bir uygulamada MongoDB gereksiz risk, büyük veri tabanlı sosyal ağ uygulamasında ise SQL gereksiz yük olur.
Sonuç olarak:

> “MongoDB hızla akan, değişken bir dünyada veriyi zincirlerinden kurtaran modern bir veri ekosistemidir.” 🌍✨