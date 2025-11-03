#### 🌱 **Spring Boot Nedir?**

Spring Boot, Java dünyasında işleri sadeleştiren bir devrimdir. 🎯 Geleneksel Spring yapısında onlarca XML ve manuel konfigürasyonla uğraşmak gerekirken, Spring Boot bu yükü sıfıra indirir. 🚀 “Convention over configuration” yani “yapılandırmadan ziyade varsayılanlar” anlayışıyla hareket eder — geliştirici yalnızca iş mantığına odaklanır. 💡 Uygulama ayağa kalkarken hangi bileşenlerin yükleneceğini, hangi portun kullanılacağını, hangi servisin başlatılacağını otomatik belirler. ⚙️ Entegre Tomcat sunucusu sayesinde dışarıdan bir server kurma ihtiyacını ortadan kaldırır. 🌐 Microservice mimarisine doğrudan uyumludur; her servis kendi başına çalışan bağımsız bir dünya gibidir. 🧱 “Auto-configuration” yapısı, projenin içeriğine göre akıllıca davranır ve gereksiz bağımlılıklar eklemez. 🧠 Sağlam bir ekosistem sunar: Actuator ile izleme, Security ile güvenlik, Data JPA ile veritabanı erişimi gibi her ihtiyacı kendi içinde barındırır. 🔒 Geliştiriciye “yaz, çalıştır, ölçekle” özgürlüğünü verir. ⚡ Kısacası Spring Boot, karmaşayı değil üretkenliği, konfigürasyonu değil akışı, ezberi değil sadeliği öğretir. 🕊️

---

#### 🚫 **Spring Boot Ne Değildir?**

Spring Boot, **yeni bir framework değildir**, Spring’in gelişmiş ve sadeleştirilmiş halidir. ⚙️ Yani “Spring’in alternatifi” değil, onu daha kolay kullanılabilir hâle getiren bir üst katmandır. 👀 Sunucu yönetimi, bağımlılık çözümleme ya da kurulum zahmeti yoktur; ama bu onu sihirli bir araç da yapmaz — hâlâ sağlam bir Java temeline ve Spring kavrayışına ihtiyaç duyarım. 🧠

---

#### 🎯 **Amacı Nedir, Bize Ne Kazandırır?**

Spring Boot’un amacı; **minimum konfigürasyonla, maksimum verimle** çalışmamı sağlamaktır. 💡 Gömülü sunucu desteği (Tomcat, Jetty vb.) ile bağımsız çalışan uygulamalar üretirim. 💥 “Jar olarak çalıştır, servisin ayakta olsun” mantığı ile klasik war deploy sürecine gerek kalmaz. Bu da bana çeviklik, zaman kazancı ve dağıtım kolaylığı sağlar. ⏱️

---

#### ⚔️ **Spring vs Spring Boot**

Spring, temeldir 🏛️; Boot ise o temele kurulan modern bir otomasyon kulesidir 🏗️. Spring’de manuel konfigürasyon (beans, XML, dependency) gereklidir. Boot ise varsayılan ayarlarıyla “ben senin yerinde ayarladım” der. 🤖 Spring’te Tomcat’i ben kurarım; Boot’ta o zaten içinde gelir. Spring altyapısı olmadan Boot var olamaz; Boot, Spring’i hızlandırır ve basitleştirir. ⚡

---

#### 🧩 **Tomcat ve Gömülü Sunucular**

Spring Boot uygulamaları, Tomcat gibi sunucuları **gömülü (embedded)** olarak barındırır. 🔌 Yani harici bir sunucuya deploy etmem gerekmez; uygulamayı jar dosyası olarak çalıştırdığımda Tomcat içerden ayağa kalkar. 🚀 Bu da “bir zip indir, tıkla, çalışsın” kolaylığı getirir.

---

#### 🧠 **DI (Dependency Injection) ve IoC (Inversion of Control) Kodun Kontrolünü Container'e Bırakmak, Gücü Kazanmak**

IoC, “kontrolün tersine çevrilmesidir” 🔄 — yani nesneleri ben yönetmem, Spring benim yerime yönetir. Ben sadece “neyin kime bağımlı olduğunu” söylerim, oluşturma işini framework yapar. 🪄 DI (Dependency Injection) ise bu kavramın uygulanış biçimidir: bir sınıfa bağımlı olduğu nesneleri dışarıdan **enjekte ederim**. Bu sayede kodum gevşek bağlı (loosely coupled), test edilebilir ve genişletilebilir olur. 💪

- IoC (Inversion of Control) aslında bir teknik kavramdan çok bir düşünce devrimidir 🔄.
- Eskiden her sınıf, kendi bağımlılıklarını kendisi oluştururdu; yani bir nesne hem kendi işini yapar hem de diğer nesneleri üretir, yönetir, hatta yok ederdi. Bu da kodu sıkı sıkıya bağlı (tightly coupled) hale getirirdi.
- IoC, bu düzeni tersine çevirir: Artık “ben yönetmem, çerçeve (framework) yönetir.” ⚙️
- Nesnelerin yaşam döngüsünü (oluşturulması, yapılandırılması, imha edilmesi) Spring üstlenir; ben sadece neyin neye ihtiyaç duyduğunu tanımlarım.
- Böylece sistem bağımlılıklardan kurtulur, modüller kendi başına gelişebilir ve değişiklikler tsunami gibi diğer katmanları etkilemez. 🌊

- DI (Dependency Injection) ise bu felsefenin pratik uygulamasıdır.
- Bir sınıfın içinde kullanacağı bağımlılıkları new ile üretmek yerine, Spring’e “şunu şuna ver” derim; Spring bu bağımlılıkları uygun biçimde enjekte eder 💉.
- Bu sayede nesneler arası ilişki, bir bağımlılık ağı değil, bir işbirliği ekosistemi haline gelir.
- Kodum hem okunabilir, hem test edilebilir, hem de genişletilebilir olur — çünkü artık her bileşen tek bir sorumluluğa sahiptir. 🧩

---

```
                         🔄 IoC & DI — KONTROLÜ TERSİNE ÇEVİRME SANATI 🔄
        ┌────────────────────────────────────────────────────────────────────────────┐
        │                                                                            │
        │     💡 KLASİK (IoC ÖNCESİ) YAPI — KOD KENDİ KADERİNİ KENDİ YAZAR ✍️        │
        │                                                                            │
        │       ┌──────────────┐          ┌──────────────┐                           │
        │       │   A Sınıfı   │──new──▶  │   B Sınıfı   │                           │
        │       └──────────────┘          └──────────────┘                           │
        │              │                                                       
        │              └─> A, B'yi kendi üretir.                                   │
        │                  Yani kontrol A’dadır. 🧩                                │
        │                                                                            │
        │        🧱 Sorun: Katı bağlanma (tight coupling), test edilemezlik,         │
        │                yeniden kullanılabilirliğin zayıflığı.                     │
        ├────────────────────────────────────────────────────────────────────────────┤
        │                                                                            │
        │     ⚙️ IoC (Inversion of Control) — KONTROLÜ TERSİNE ÇEVİR! 🔁             │
        │                                                                            │
        │       ┌──────────────┐              ┌───────────────────────────────┐      │
        │       │   A Sınıfı   │              │         SPRING CONTAINER       │      │
        │       └──────────────┘              └───────────────────────────────┘      │
        │              ▲                                     │                     │
        │              │    “B’yi bana sen ver.” 🙋‍♂️          │ B nesnesi oluşturulur  │
        │              │                                     ▼                     │
        │              └─────────────────────────── B Sınıfı (Bean) ⬅──────────────┘
        │                                                                            │
        │        🧩 Artık A, B'nin nasıl üretildiğini bilmez.                        │
        │            Sadece “bir B isterim” der.                                    │
        │            Spring Container (IoC) yönetimi devralır. 🪄                   │
        ├────────────────────────────────────────────────────────────────────────────┤
        │                                                                            │
        │     💉 DI (Dependency Injection) — BAĞIMLILIĞIN ENJEKSİYONU                │
        │                                                                            │
        │         ┌───────────────────────────── SPRING CONTAINER ───────────────────┐
        │         │                                                                 │
        │         │   ┌──────────────┐       inject()       ┌──────────────┐        │
        │         │   │   B Sınıfı   │ ───────────────────▶ │   A Sınıfı   │        │
        │         │   └──────────────┘                      └──────────────┘        │
        │         │         ↑                                          │            │
        │         │         └── @Autowired / @Inject / @Qualifier       │            │
        │         └─────────────────────────────────────────────────────┘            │
        │                                                                            │
        │      🌿 Artık bağımlılıklar dışarıdan “enjekte edilir”.                   │
        │      🔍 Kod gevşek bağlıdır (loose coupling).                             │
        │      🧪 Test kolaydır (mock veya fake nesne verilebilir).                 │
        │      🧩 Sistem modülerdir, değişiklik dalga etkisi yaratmaz.              │
        └────────────────────────────────────────────────────────────────────────────┘
```

---

#### 🏗️ **Çok Katmanlı Mimari (Layered Architecture)**

Çok katmanlı mimari, kodun **rollerine göre ayrılması** prensibidir. 📚 Her katmanın bir görevi vardır ve diğerlerine doğrudan karışmaz. Bu sayede sistem ölçeklenebilir, bakımı kolay ve yeniden kullanılabilir olur. Eğer kullanılmazsa kodlar birbirine karışır, hatayı izlemek kabusa döner. 💥 Bu yapı sayesinde her katman **bağımsız gelişir**, test edilir ve gerektiğinde değiştirilir. 🔄

---

```
               🎯 Çok Katmanlı Mimari Şeması 🎯
        ┌─────────────────────────────┐
        │         Controller          │  👂 Kullanıcı isteğini alır
        ├─────────────────────────────┤
        │           Service           │  🧠 İş mantığını yönetir
        ├─────────────────────────────┤
        │         Repository          │  💾 Veritabanı işlemlerini yapar
        ├─────────────────────────────┤
        │           Model             │  🧱 Veriyi temsil eder (Entity)
        └─────────────────────────────┘
```

---

```
                   🎯 ÇOK KATMANLI MİMARİ ŞEMASI 🎯

        ┌─────────────────────────────────────────────────────────────────────┐
        │                          Controller                                 │  
        │  👂 Kullanıcıdan gelen isteği ilk karşılayan katmandır. İstek,      │
        │  burada analiz edilir, yönlendirilir ve uygun servise iletilir.     │
        │  Gelen verilerin doğruluğu, biçimi ve güvenliği kontrol edilir.     │
        │  Kullanıcıya dönecek yanıt ise burada biçimlendirilip sunulur.      │
        │  Yani Controller, sistemin dış dünyaya açılan kulağı ve ağzıdır.🗣️  │
        ├─────────────────────────────────────────────────────────────────────┤
        │                             Service                                 │
        │  🧠 Uygulamanın kalbi gibidir; iş mantığı burada atar.              │
        │  Controller’dan gelen istekleri yorumlar, gerekli kuralları         │
        │  uygular, hesaplamaları yapar ve Repository katmanıyla konuşur.     │
        │  Servis, sistemi düzenleyen, akışı yöneten “beyin” görevindedir.    │
        │  İş kurallarını merkezileştirerek kod tekrarını önler.⚙️            │
        ├─────────────────────────────────────────────────────────────────────┤
        │                           Repository                                │
        │  💾 Veritabanı işlemlerinin yürütüldüğü katmandır. CRUD             │
        │  (Create, Read, Update, Delete) operasyonlarını gerçekleştirir.     │
        │  Service katmanından gelen istekleri SQL sorgularına dönüştürür     │
        │  ve veriyi Model katmanına taşır. Veriyle konuşan “hafıza           │
        │  yöneticisi” gibidir. Güvenli, düzenli ve soyutlanmış erişim sağlar.🔐│
        ├─────────────────────────────────────────────────────────────────────┤
        │                             Model                                   │
        │  🧱 Sistemdeki verilerin gerçek hayattaki varlıklarını temsil       │
        │  eder (örneğin: Kullanıcı, Ürün, Sipariş). Entity sınıfları,        │
        │  veritabanı tablolarının nesne karşılığıdır. Model katmanı,         │
        │  verinin biçimini, alanlarını ve ilişkilerini tanımlar.             │
        │  Yani bu katman, yazılımın “iskelet yapısı”dır. 🦴                  │
        └─────────────────────────────────────────────────────────────────────┘

🔁 Katmanlar arası bağımlılıklar tek yönlüdür:
Controller → Service → Repository → Model  
Her katman sadece alt katmanı bilir, üst katmanı asla tanımaz. Bu, sistemin
sürdürülebilirliğini, test edilebilirliğini ve ölçeklenebilirliğini garantiler. 🧩
```

---

#### 🌐 **HTTP Metotları (GET, POST, PUT, DELETE)**

Her bir HTTP metodu, istemci (client) ile sunucu (server) arasındaki konuşmanın farklı bir niyetini temsil eder.
REST mimarisi bu metotları bir “sözleşme” olarak kullanır — hangi eylemin, hangi amaçla çağrıldığını açıkça söyler.
Bu sayede sistem, tahmin edilebilir, standart ve anlaşılabilir bir hale gelir. 🌍

***GET 🧐***
- “Oku ama dokunma.” — Bu metodun doğası salt veri çekmektir.
- Sunucudan kaynak isterim; sistemin durumunu değiştirmem, sadece gözlemlerim.
- Tarayıcıda bir URL yazdığımda aslında her seferinde bir GET isteği gönderirim.
- Bu metot cache edilebilir, çünkü güvenlidir (idempotent).

***POST ✍️***
- “Yeni bir şey ekle.” — Veritabanına yeni bir kayıt oluşturmak, yeni bir kaynak yaratmak için kullanılır.
- Gönderdiğim veri, sunucu tarafında işlenir ve genellikle bana oluşturulan kaynağın kimliğini döner (örneğin bir id).
- POST, sistemi değiştirir; bu yüzden dikkatli kullanılmalıdır.
- Aynı isteği iki kez yollarsam, iki ayrı kayıt oluşabilir (non-idempotent). ⚠️

***PUT 🧱***
- “Var olanı tamamen değiştir.” — Sunucuda zaten var olan bir kaynağı, gönderdiğim yeni veriyle tamamen güncellerim.
- Eğer kaynak yoksa bazı sistemler PUT ile onu da oluşturabilir.
- PUT istekleri idempotenttir, yani aynı isteği defalarca göndersem de sonuç hep aynıdır.
- Gerçek hayatta bir dosyayı “üzerine kaydetmek” gibidir. 💾

***DELETE 🗑️***
- “Sil ve sistemden kaldır.” — Belirtilen kaynağı tamamen yok eder.
- Sunucudan “artık bu veri bana gerek yok” mesajıdır.
- DELETE de idempotenttir; bir veriyi ikinci kez silmek istesem bile artık yoktur.
- Sistem, bu metodla temizlenir, hafifler ve düzenli kalır. 🧹

---

#### 🧾 **@RequestBody, @RestController, @Controller, @RequestMapping, @PathVariable**

***@Controller 🎭***
- MVC (Model-View-Controller) yapısının “View”e bakan yüzüdür.
- Kullanıcıdan gelen isteği yakalar, iş mantığını çağırır ve sonucunu bir görünüme (HTML, JSP, Thymeleaf vs.) döndürür.
- Yani sahnedeki aktör gibidir — veriyle etkileşimi sahnede gösterir.
- Web uygulamalarında sayfa tabanlı etkileşimlerin temel taşıdır. 🎬

***@RestController 🔥***
- Modern RESTful API’lerin bel kemiğidir.
- @Controller + @ResponseBody birleşimidir.
- Yani döndüğü sonuç bir sayfa değil, ham veri (JSON, XML) olur.
- Mobil uygulamalar, frontend framework’leri (Angular, React vb.) veya başka servisler bu veriyi tüketir.
- Kısaca: Görüntü sunmaz, veri konuşur. 💬

***@RequestMapping 🧭***
- URL adreslerini metotlarla eşleştiren bir yönlendirme haritasıdır.
- Örneğin @RequestMapping("/users") dediğimde, artık /users adresine gelen istek o metoda yönlendirilir.
- Ayrıca method = RequestMethod.GET gibi parametrelerle hangi HTTP metoduna yanıt vereceğini belirtebilirim.
- Uygulamanın dış dünya ile iletişim rotasını çizer, bir nevi API haritasını oluşturur. 🗺️

***@RequestBody 📦***
- İstek gövdesinde (body) gelen JSON verisini alır ve otomatik olarak Java nesnesine dönüştürür.
- Spring, burada Jackson gibi kütüphaneleri kullanarak JSON → Object dönüşümünü benim yerime yapar.
- Bu anotasyon sayesinde, manuel parsing derdinden kurtulurum.
- Kısacası, veriyi doğrudan “içeri alırım”, dönüştürmekle uğraşmam. 🚪

***@PathVariable 🧩***
- URL’nin içindeki değişken kısmı yakalamamı sağlar.
- Örneğin /users/10 gibi bir adreste 10 değerini @PathVariable Long id şeklinde doğrudan alabilirim.
- Bu sayede parametreler query string içinde değil, daha anlamlı URL’ler içinde taşınır (örnek: /user/5/orders).
- REST mimarisinde kaynakları “doğal dille” temsil etmenin en estetik yoludur. 🧭

---

#### 🔗 **Katmanlar Arası Bağımsızlık**

Controller sadece Service’i, Service sadece Repository’yi bilir. ⚖️ Model (Entity) hiçbir katmandan haberdar değildir. Bu izolasyon, sistemin sürdürülebilirliğini artırır; bir modül değiştiğinde diğerleri etkilenmez. 🧱 Kod bir ekosistem gibi işler, parça değişse de sistem çökmemelidir. 🌿

---

#### ⚙️ **application.properties Satırlarının Anlamı**

```
spring.application.name = 037_spring-boot-hello
```

➡️ Uygulamanın adını belirler, loglarda ve sistem içinde bu isimle görünür. 🧾

```
server.port = 9091
```

➡️ Uygulamanın çalışacağı portu belirler; tarayıcıdan `localhost:9091` ile ulaşırım. 🌍

```
spring.datasource.url = jdbc:mysql://localhost:3306/huseyin_aydin_db
```

➡️ Veritabanının bağlantı adresidir, burada MySQL kullanılmış. 🗃️

```
spring.datasource.username = root
```

➡️ Veritabanına bağlanırken kullanılacak kullanıcı adıdır. 👤

```
spring.datasource.password = toor
```

➡️ Veritabanı şifresidir, güvenli saklanması gerekir. 🔒

```
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
```

➡️ JDBC’nin MySQL ile iletişim kurmasını sağlayan sürücü sınıfıdır. 🚗

```
spring.data.jdbc.dialect = mysql
```

➡️ SQL komutlarının MySQL’e uygun biçimde çalışmasını sağlar. 🧩

```
spring.jpa.hibernate.ddl-auto = update
```

➡️ Entity sınıflarındaki değişiklikleri tabloya otomatik olarak yansıtır. 🔁

```
spring.jpa.show-sql = true
```

➡️ Çalışan SQL sorgularını konsolda gösterir; hata ayıklamada çok değerlidir. 👀

---

🧭 **Son Söz:**
Spring Boot, yazılımcıya “mekanik işleri bana bırak, sen mantığa odaklan” der. Kodun modülerliği, mimarinin katmanlı yapısı ve IoC yaklaşımı sayesinde proje bir bina gibi yükselir — sağlam temeller, net katmanlar, güçlü sütunlar. 🏛️