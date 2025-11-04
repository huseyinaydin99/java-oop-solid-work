#### ⚙️ **Spring Boot Exception Yönetimi ve Temel Kavramlar**

---

#### 🌿 **Spring Profilleri**

Spring profilleri, bir uygulamanın farklı çalışma ortamlarında (örneğin “dev”, “test”, “prod”) farklı yapılandırmalarla çalışabilmesini sağlar. Bir nevi kişilik gibidir, sistem hangi ortamdaysa o profile özgü davranışlar sergiler.
Bir `application-dev.yml` dosyası geliştirme ortamında test veritabanına bağlanırken, `application-prod.yml` dosyası canlı sistemin Oracle veya PostgreSQL veritabanına bağlanır.
Bu yaklaşım, yapılandırma esnekliğini ve sürümleme disiplinini artırır. 🎭
Aktif profili seçmek için `spring.profiles.active=dev` gibi bir parametre kullanılır. Böylece uygulama aynı kodla, ama farklı kimliklerle koşar.

---

#### 🫘 **Bean Nedir?**

Spring’te “bean”, framework tarafından yönetilen bir nesnedir. Yani nesnenin yaşam döngüsü, bağımlılıkları ve oluşturulması benim kontrolümde değildir; Spring Container’ın elindedir. 🧩
Ben sadece sınıfa `@Component`, `@Service` veya `@Repository` gibi anotasyonlar ekleyerek Spring’e “bunu yönet” derim.
Spring, ihtiyaç duyulan yerde bu nesneyi oluşturur ve Dependency Injection (bağımlılık enjeksiyonu) ile enjekte eder.
Bu, sistemin loosely coupled (gevşek bağlı) olmasını sağlar ve test edilebilirliği artırır.
Bean’ler arasındaki ilişkileri Spring yönetir; ben sadece davranışı tanımlarım. Bu da kodu sadeleştirir, tekrarı ortadan kaldırır ve sürdürülebilir hale getirir. 🔄

---

#### 💥 **Spring Boot Exceptions (İstisna Yönetimi)**

Spring Boot’ta istisna yönetimi (exception handling), uygulamanın en kritik parçalarından biridir çünkü hatayı gizlemeden, ama sistemin çökmesini de engelleyerek yönetmeyi sağlar. 🧯
Framework, runtime sırasında oluşan hataları `ResponseEntityExceptionHandler` gibi temel sınıflar üzerinden yakalayabilir. Ancak bu bazen yetersizdir — çünkü her uygulamanın kendi özel hata yapısı olmalıdır.
Ben genellikle `@ControllerAdvice` anotasyonunu kullanarak merkezi bir hata yönetim katmanı oluştururum. Bu, uygulamanın farklı controller’larında oluşan tüm hataları tek bir yerden yakalar ve JSON formatında kullanıcıya anlamlı bir cevap döner.
Örneğin, `@ExceptionHandler(EntityNotFoundException.class)` diyerek belirli türdeki hataları yakalarım ve `ResponseEntity` içinde uygun bir HTTP statüsü (örneğin `404 NOT FOUND`) dönerim.
Bu yapı sayesinde hem log yönetimi düzenli olur hem de kullanıcı deneyimi profesyonel görünür.
Spring Boot ayrıca `@RestControllerAdvice` anotasyonuyla REST API’lere özel, sade bir hata yönetim modeli sunar.
İstisna yönetimi, sistemin çökmesini engelleyen görünmez bir kalkan gibidir. 🛡️

---

#### ⚖️ **@ControllerAdvice Dipnotu**

- `@ControllerAdvice`, uygulama genelinde exception’ları yakalamak için kullanılan özel bir anotasyondur.
- Bu anotasyon, tüm controller’ları dinler ve birinde hata olduğunda araya girip kontrolü devralır.
- Yani `try-catch` bloklarını her controller’a yazmak yerine, merkezi bir yapı kurmamı sağlar.
- Bunun içinde genellikle `@ExceptionHandler`, `@ModelAttribute` veya `@InitBinder` metotları bulunur.
- Bu yaklaşım hem loglamayı düzenler hem de kullanıcıya tek tip hata cevabı sunar. 🎯
- @RestControllerAdvice, @ControllerAdvice'ın @ResponseBody ile birleşmiş halidir ve bu nedenle REST servislerinde kullanıldığında metotların çıktıları otomatik olarak JSON/XML'e dönüştürülür.
- Kısaca, `@ControllerAdvice` bir uygulamanın sinir sistemidir — her yerden gelen acıyı tek bir merkezde algılar ve uygun tepkiyi verir.

- @ControllerAdvice -> MVC (HTML view'lar dönen) kontroller için.
- @RestControllerAdvice -> REST (JSON/XML dönen) kontroller için.

---

#### 🧾 **@Transactional ve @Transactional(readOnly = true)**

`@Transactional` anotasyonu, bir metodun veya sınıfın veritabanı işlemlerinin **tek bir işlem (transaction)** olarak yürütülmesini sağlar.
Yani metodun içindeki SQL işlemleri tamamlanmadan hata oluşursa, yapılan tüm değişiklikler geri alınır (rollback). 💣
Bu da veri tutarlılığını korur.
Eğer `@Transactional(readOnly = true)` eklenirse, bu metodun sadece **okuma amaçlı** çalıştığı belirtilmiş olur; performans optimizasyonu sağlar çünkü Hibernate veya JPA gereksiz kilitleri devre dışı bırakır.
Yani `readOnly` parametresi, “sadece oku, yazma işlemi yapma” anlamına gelir.
Bu da sorguların daha hızlı çalışmasını sağlar. ⚡

---

#### 🧠 **@Service Anotasyonu**

`@Service`, iş mantığını (business logic) barındıran sınıfları tanımlamak için kullanılır.
Spring Container, bu anotasyona sahip sınıfları otomatik olarak tespit eder ve bir bean olarak yönetir.
Controller katmanı kullanıcıdan gelen isteği alır, service katmanına iletir; service katmanı da repository üzerinden veritabanı işlemlerini yürütür.
Bu ayrım, **katmanlı mimari**yi destekler ve kodun yeniden kullanılabilirliğini artırır.
`@Service` ayrıca, AOP (Aspect-Oriented Programming) ile hata yakalama, performans ölçümü veya loglama gibi cross-cutting concern’lerin enjekte edileceği ideal noktadır. 🎛️

---

#### 🚀 **@SpringBootApplication**

`@SpringBootApplication`, Spring Boot’un kalbidir.
Bu anotasyon, üç farklı anotasyonu tek çatı altında toplar:

* `@Configuration` → Uygulama yapılandırmalarını tanımlar.
* `@EnableAutoConfiguration` → Spring Boot’un otomatik yapılandırmasını etkinleştirir.
* `@ComponentScan` → Belirtilen paket altındaki bean’leri otomatik olarak tarar.
  Yani `@SpringBootApplication`, projenin “ana girişi”dir — bir orkestra şefi gibi, tüm bileşenlerin birbiriyle uyumlu çalışmasını sağlar. 🎼
  Main sınıfına yerleştirilir ve `SpringApplication.run()` metodu ile tüm ekosistemi ayağa kaldırır.

---

#### 🧩 **İstisna Yakalama Şeması**

```
           🚦 İstek (Request)
                    │
                    ▼
          🎯 Controller Katmanı
                    │
                    ▼
          ⚙️ Service Katmanı (@Transactional)
                    │
                    ▼
          🗄️ Repository Katmanı
                    │
                    ▼
      💥 Hata oluştu! (örneğin DataIntegrityViolationException)
                    │
                    ▼
       🧠 @ControllerAdvice devreye girer
                    │
                    ▼
      🧾 @ExceptionHandler yakalar ve JSON Response döner
                    │
                    ▼
          ✅ Kullanıcıya Anlamlı Hata Yanıtı
```

---

#### 🔚 **Sonuç**

Spring Boot’ta exception yönetimi, sadece hata yakalama değil; sistemin profesyonel, dayanıklı ve kullanıcı dostu davranmasının temelidir.
Profil yönetimiyle ortamlar arası tutarlılık, bean yapısıyla yönetilebilirlik, transaction yönetimiyle veri bütünlüğü, `@ControllerAdvice` ile merkezi hata kontrolü sağlanır.
Bu bütünlük, uygulamayı sadece çalışan değil — yaşayan bir organizma haline getirir. 🌱

---

---

#### ⚙️ **Spring Boot Uygulama Özellikleri ve Profillerin Anlamı**

---

#### 🌱 **Profil Tanımlamaları (`spring.profiles.active`)**

Spring, bir uygulamanın farklı ortamlarda (örneğin geliştirme, test, üretim) farklı ayarlarla çalışabilmesi için “profil” adını verdiği mantıksal bölümler kullanır.
Bu ayarlar, `application.yml` veya `application.properties` dosyalarında `spring.profiles.active` satırıyla seçilir.
Her profil, sistemin çalışma ortamına özgü yapılandırma ve bağlantı bilgilerini içerir. 🌍

* `#spring.profiles.active = dev` → Bu satır aktif edilirse uygulama “geliştirme ortamı”nda çalışır. Bu ortamda genellikle test verileri ve log çıktıları ayrıntılı olur.
* `#spring.profiles.active = test` → Bu profil test aşamasında kullanılır; test veritabanı ve sahte (mock) servislerle çalışmak için idealdir.
* `#spring.profiles.active = prod` → Bu, canlı sistem profilidir. Gerçek kullanıcı verileri, performans ve güvenlik ayarlarıyla çalışır.
* `spring.profiles.active = dev-mysql` → Bu satır, geliştirme ortamının MySQL veritabanına bağlanan versiyonunu aktif eder.
* `#spring.profiles.active = dev-postges` → Bu profil yorum satırında, PostgreSQL kullanan geliştirme ortamı için bir alternatiftir.

Kısacası, bu ayarlar uygulamanın hangi ortamda hangi veritabanı, port, log seviyesi veya güvenlik politikasıyla çalışacağını belirler. 🎭

---

#### 📘 **Swagger Arayüzü Ayarları**

Swagger, REST API’lerin belgelenmesi ve test edilmesi için kullanılan bir araçtır.
Aşağıdaki satırlar, Swagger kullanıcı arayüzünün (Swagger UI) nasıl görüneceğini ve hangi URL üzerinden erişileceğini belirler:

* `springdoc.swagger-ui.path = /swagger-ui.html` → Swagger arayüzüne **`http://localhost:9095/swagger-ui.html`** adresinden erişilebileceğini belirtir.
* `springdoc.show-actuator = true` → Uygulamanın Actuator uç noktalarını Swagger üzerinden görünür hale getirir.
  Bu sayede hem API hem de sağlık (health) kontrolleri tek ekrandan izlenebilir. 📊

---

#### 🔍 **Actuator Ayarları**

Spring Boot Actuator, uygulamanın iç durumunu gözlemlemek için kullanılan bir araçtır.
Performans, bellek, bağlantı, sağlık durumu (health check) gibi bilgiler sağlar.
Aşağıdaki satırlar, Actuator’un hangi URL üzerinden erişileceğini ve hangi uç noktaların açılacağını belirtir:

* `management.endpoints.web.base-path = /actuator` → Actuator uç noktalarının **`/actuator`** kök yolunda çalışacağını belirtir.
* `management.endpoints.web.exposure.include = *` → Tüm Actuator uç noktalarının (örneğin `/actuator/health`, `/actuator/info`, `/actuator/metrics`) erişime açık olduğunu ifade eder.

Bu yapı genellikle sistemin sağlık kontrolü (health monitoring) için kullanılır. 🩺

---

#### 🧱 **Uygulama İsmi ve Port Ayarı**

* `spring.application.name = java-36-spring-boot-exception`
  Bu satır, Spring Boot uygulamasının adını belirler. Özellikle mikroservis yapılarında, sistemin birbirini tanıması için önemlidir.
* `server.port = 9095`
  Uygulamanın HTTP üzerinden dinleyeceği port numarasını belirtir. Varsayılan port 8080’dir; bu satırla 9095 olarak değiştirilmiştir.

Başka bir örnekte:

* `spring.application.name = java-38-spring-boot-exception`
* `server.port = 9090`
  Bu durumda ikinci profil, farklı bir port ve isimle çalışır; böylece iki proje aynı anda çakışmadan koşabilir. 🚀

---

#### 🗄️ **Veritabanı (Datasource) Ayarları**

Spring Boot, veritabanı bağlantısını otomatik yönetir. Aşağıdaki satırlar, sistemin hangi veritabanına bağlanacağını ve hangi sürücüyü kullanacağını belirtir:

#### 🧩 **MySQL Bağlantı Ayarları**

```
spring.datasource.url = jdbc:mysql://localhost:3306/huseyin_aydin_db
spring.datasource.username = root
spring.datasource.password = toor
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.data.jdbc.dialect = mysql
```

Bu yapı, uygulamanın yerel MySQL veritabanına bağlanmasını sağlar.
Dialect (diyalekt) kısmı, Spring’in SQL cümlelerini MySQL diline uygun şekilde oluşturmasını sağlar.
Yani Hibernate, MySQL’in anladığı sözdizimine göre sorgular üretir. ⚙️

---

#### 🧩 **PostgreSQL Bağlantı Ayarları**

```
spring.datasource.url = jdbc:postgresql://localhost:5432/huseyin_aydin_db?currentSchema=development
spring.datasource.username = postgres
spring.datasource.password = toor
spring.datasource.driver-class-name = org.postgresql.Driver
spring.data.jdbc.dialect = postgresql
```

Bu yapı PostgreSQL veritabanına bağlanır.
`currentSchema=development` parametresi, “development” adlı şema içinde işlem yapılacağını belirtir.
Bu sayede birden fazla şema (örneğin test, prod) aynı veritabanında barınabilir. 🧠

---

#### 🧮 **Hibernate Ayarları**

* `spring.jpa.hibernate.ddl-auto = update` → Hibernate, tablo yapısını model sınıflarına göre günceller. Eksik tablo veya kolon varsa otomatik oluşturur.
* `spring.jpa.show-sql = true` → Çalışan SQL sorgularını konsolda gösterir. Bu, geliştirme sürecinde büyük kolaylık sağlar. 🔍

Bu ayarlar sayesinde uygulama, JPA (Java Persistence API) aracılığıyla veritabanına sorunsuz bağlanır.
Manuel tablo oluşturma ihtiyacı ortadan kalkar ve loglar üzerinden yapılan sorgular gözlemlenebilir.

---

#### 🔧 **Tüm Yapının Özet Şeması**

```
        ⚙️ application.properties
                   │
                   ▼
       🌿 spring.profiles.active → dev-mysql
                   │
                   ▼
       🗄️ spring.datasource.* (MySQL veya PostgreSQL)
                   │
                   ▼
       🧱 spring.jpa.* (Hibernate Ayarları)
                   │
                   ▼
       🔍 management.* (Actuator İzleme)
                   │
                   ▼
       📘 springdoc.* (Swagger UI)
                   │
                   ▼
       🚀 Uygulama Başlatıldı (Port: 9095)
```

---

#### 🔚 **Sonuç**

Bu yapılandırmalar bir araya geldiğinde, Spring Boot uygulaması çevik, izlenebilir ve yönetilebilir bir hale gelir.
Profil sistemi farklı ortamları ayırır, Actuator canlı izleme imkânı sunar, Swagger API’leri dokümante eder, veritabanı ayarları ise JPA üzerinden tam otomatik çalışır.
Her satır, yazılımcıya kontrol değil — konfor kazandırır. 🧩✨