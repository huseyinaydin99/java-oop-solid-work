#### 🛡️ SPRING SECURITY VE JWT — GÜVENLİĞİN İKİ KUTBU ⚔️

Yazılım geliştirme dünyasında **güvenlik**, yalnızca “kullanıcı girişini doğrulamak”la sınırlı bir kavram değildir.  
O, sistemin iç yapısını koruyan, erişimi yöneten ve her isteği kimliklendirerek **uygulamanın bütünlüğünü** sağlayan görünmez bir kalkandır. 🧠

Bu dokümanda, **Spring Security** ve **JWT (JSON Web Token)** teknolojilerini derinlemesine ele alıyorum.  
İkisi de birbirinden farklı ama birbirini tamamlayan iki güvenlik paradigmasıdır — biri **kuralları koyar**, diğeri **kimliği taşır**.  
Burada yazdıklarım; yalnızca teknik dokümanlardan değil, bizzat **gerçek projelerde edindiğim deneyimlerden** ve **sistemlerde yaşadığım sınır durumlarından** süzülmüştür. 🎯

---

#### 🧱 SPRING SECURITY NEDİR, NE DEĞİLDİR?

Spring Security, sıradan bir "login kütüphanesi" değildir; o, **uygulama ekosisteminin güvenlik duvarıdır.**  
HTTP isteklerini karşılayan her endpoint’in arkasında, görünmez bir **filtre zinciri (filter chain)** vardır.  
Bu zincir, bir isteğin önce kim olduğunu, sonra neyi yapmaya yetkisi olduğunu belirler. 🔍

Spring Security'nin asıl gücü, geliştiriciye “güvenliği düşünmeden” güvenli sistemler kurma olanağı vermesidir.  
Yani iş mantığına odaklanırken, kimlik doğrulama ve erişim kontrolü gibi karmaşık süreçleri **çerçevenin kendisi** yönetir.

---

#### 🔒 **Amaç — Merkezileşmiş ve Ölçeklenebilir Güvenlik Yönetimi**

Spring Security’nin temel amacı, **authentication (kimlik doğrulama)** ve **authorization (yetkilendirme)** süreçlerini  
tek bir çatı altında toplamak, merkezi hale getirmek ve uygulamanın ölçeği büyüdükçe bile bu kontrolü korumaktır.

- Her talebin kimden geldiğini belirler.
- Hangi kaynaklara erişilebileceğini tanımlar.
- Rol, yetki ve oturum mantığını tutarlı biçimde yönetir.
- Geliştiriciyi manuel kontrol kodlarından kurtarır.

Bu, özellikle büyük kurumsal yapılarda, güvenlik politikasının **tek noktadan yönetilmesi** anlamına gelir. 🏢

---

#### ⚠️ **Kullanılmazsa Ne Olur?**

Spring Security gibi bir yapı olmadan geliştirici, tüm güvenliği **manuel olarak yazmak zorunda kalır.**  
Yani her controller’da, her endpoint’te ayrı ayrı kullanıcı kontrolü, yetki doğrulaması ve erişim denetimi yapılır.

Bu durum:
- 🚫 **Kod tekrarına**,
- 💣 **Güvenlik açıklarına**,
- 🧩 **Bakımı zor, karmaşık yapılara**,
- 🕳️ Ve çoğu zaman **insan hatasına** yol açar.

Kısacası, Spring Security olmadan sistemde güvenlik **yama gibi dağılır**; her yerde bir parça vardır ama hiçbirinde bütünlük yoktur.

---

#### ⚙️ **Ana Amacı — Katmanlı Güvenlik Duvarı**

Spring Security’nin en güçlü yanı, **güvenliği iş mantığından tamamen ayırmasıdır.**  
Kodun geri kalanı, “bu işlemi kim yapabilir?” sorusunu düşünmez — çünkü cevabı framework verir.

Bu sayede:
- Controller ve Service katmanları sade kalır,
- Roller (`ROLE_ADMIN`, `ROLE_USER` vb.) soyutlanır,
- Yetkilendirme merkezi hale gelir,
- Kodun test edilebilirliği ve sürdürülebilirliği artar.

Yani “güvenliği kodla değil, mimariyle çözmek” prensibi doğrudan uygulanmış olur.

---

#### 💡 **Ne Katar — Yazılımcıya Disiplin ve Görünmeyen Güven**

Spring Security, yalnızca bir araç değil, bir **bakış açısı kazandırır.**  
Artık yazılımcı, “veriyi nasıl çekerim?” yerine “bu veriye kim erişebilir?” diye düşünmeye başlar.  
Bu zihinsel dönüşüm, yazılımı **güvenli, denetlenebilir ve politikalarla yönetilebilir** hale getirir.

Ayrıca:
- Kod tekrarını azaltır.
- Güvenliği merkezi bir noktadan yönetmeyi öğretir.
- Mimaride **ayrık sorumluluk prensibini (SoC)** güçlendirir.
- Ekibin tamamına ortak bir güvenlik kültürü kazandırır.

Böylece ekip, yalnızca kod yazan değil, **sistemi koruyan geliştiriciler topluluğu** haline gelir. 👥

---

#### 🏗️ **Avantajları — Kalkanın Gücü 🛡️**

| Özellik | Açıklama |
|----------|-----------|
| 🔁 **Filter Chain Mimarisi** | Her HTTP isteği, sıralı filtreler zincirinden geçer. Bu sayede kimlik doğrulama, yetkilendirme, CORS, CSRF gibi kontroller birbirinden ayrışmış ama koordinelidir. |
| 🧩 **Role-Based Access Control (RBAC)** | Kullanıcıların rollerine göre erişim politikaları tanımlanabilir. Bu da güvenlik yönetimini modüler ve şeffaf hale getirir. |
| 🧱 **Yerleşik Güvenlik Katmanları** | CSRF, XSS, Session Hijacking gibi yaygın saldırılara karşı koruma mekanizmaları dahili olarak sunulur. |
| 🧠 **Annotation Tabanlı Yapı** | `@PreAuthorize`, `@Secured`, `@EnableWebSecurity` gibi açıklayıcı dipnotlarla güvenlik politikaları kodla bütünleşir. |
| ⚙️ **Test Edilebilirlik ve Esneklik** | Güvenlik modülleri MockMvc veya TestContainers ile izole test edilebilir, farklı senaryolar kolayca simüle edilir. |

---

#### ⚠️ **Dezavantajları — Gücün Bedeli**

- 📘 Öğrenme eşiği yüksektir; kavramlar soyut ve çok katmanlıdır.
- 🔄 Yanlış yapılandırma, erişim kilitlenmelerine veya beklenmedik `403 Forbidden` hatalarına yol açabilir.
- 🧩 JWT, OAuth 2.0, LDAP gibi sistemlerle entegre edilirken **filter sıralaması (order)** doğru ayarlanmazsa kimlik doğrulama akışı bozulabilir.
- 🔐 Geliştirici güvenlik mantığını anlamadan kopyaladığı konfigürasyonlarla **farkında olmadan açık bırakabilir.**

---

```

                🛡️ SPRING SECURITY + JWT — YATAY MİMARİ AKIŞI 🔄
───────────────────────────────────────────────────────────────────────────────
🌍 CLIENT
│
▼
[ HTTP REQUEST ]
│
▼
═══════════════════════════════════════════════════════════════════════════════
🔰 SPRING SECURITY FILTER CHAIN
═══════════════════════════════════════════════════════════════════════════════
│ 1️⃣ JwtAuthFilter ──► 2️⃣ AuthenticationManager ──► 3️⃣ AuthenticationProvider │
│        │                        │                         │                  │
│        ▼                        ▼                         ▼                  │
│  Token çözümlenir 🧩     Doğrulama isteği atar ⚙️     UserDetailsService 👤  │
│  (Header’daki JWT okunur) (Authentication objesi)   (Kullanıcı DB’den çekilir)│
═══════════════════════════════════════════════════════════════════════════════
│
▼
[ ✅ SecurityContextHolder ] → (Kimlik doğrulandı, kullanıcı context’e eklendi)
│
▼
═══════════════════════════════════════════════════════════════════════════════
🔓 AUTHORIZATION KATMANI
───────────────────────────────────────────────────────────────────────────────
@PreAuthorize("hasRole('ADMIN')") ─► Rol / Yetki kontrolü yapılır  
Erişim izni yoksa ❌ 403 Forbidden, varsa ✅ Controller’a yönlendirilir  
═══════════════════════════════════════════════════════════════════════════════
│
▼
⚙️ CONTROLLER / SERVICE  
├─ İş mantığı çalışır  
├─ Veritabanı işlemleri yapılır  
└─ Gerekirse yeni JWT üretilir 🔑  
═══════════════════════════════════════════════════════════════════════════════
│
▼
[ HTTP RESPONSE ]  →  (200 OK) →  JWT veya JSON body →  🌍 CLIENT geri döner
───────────────────────────────────────────────────────────────────────────────

🧩 ÖZET:
Client → JwtFilter → AuthManager → AuthProvider → UserDetailsService  
→ SecurityContextHolder → Authorization → Controller → Response

───────────────────────────────────────────────────────────────────────────────
🧠 KRİTİK NOKTALAR:
- `SecurityConfig` → Güvenlik duvarı, endpoint izinleri, filter sıralaması
- `JwtUtils` → Token üretimi, çözümü, geçerlilik kontrolü
- `PasswordEncoder` → BCrypt / Argon2 ile hash karşılaştırması
- `SecurityContextHolder` → Kullanıcının kimliğini request boyunca taşır
- `SessionCreationPolicy.STATELESS` → Oturum tutulmaz, sistem tamamen stateless  
  ───────────────────────────────────────────────────────────────────────────────
```

---

#### 🧠 KISACA ÖZETLERSEK

Spring Security, sadece bir kütüphane değil, **uygulamanın vicdanıdır.**  
O, her isteğin arkasında “bu kullanıcı kimdir, bunu yapmaya hakkı var mı?” diye soran görünmez bir bekçidir.  
Doğru kullanıldığında uygulamayı saldırılara karşı sağlamlaştırır, yanlış anlaşıldığında ise “güvenli sanılan ama kapıları aralık” bir yapıya dönüşür.

> 💬 **Bir sistemde güvenlik, sonradan eklenen bir özellik değil; en baştan planlanan bir mimaridir.**  
> Spring Security, bu planı gerçeğe dönüştürmenin en olgun yoludur. 🔒

---

#### 🔐 JWT (JSON WEB TOKEN) — MODERN GÜVENLİKTE STATLESS DEVRİ 🌍

Günümüzde uygulama güvenliği sadece "kullanıcı adı ve şifre kontrolü" değildir; sistemin **kendi kimliğini yönetebilme becerisi** haline gelmiştir.  
İşte bu noktada **JWT (JSON Web Token)**, modern mimarilerin bel kemiği olarak karşımıza çıkar.  
Onu anlamak, sadece bir “token” yapısını değil, **güvenliğin merkezsizleşmesini** anlamaktır. ⚙️
>Stateless, sunucunun kullanıcıya ait hiçbir oturum (session) bilgisini saklamadığı, her isteğin kimliğini kendi taşıdığı bir çalışma modelidir. 🔐

---

#### 🧱 JWT NEDİR, NE DEĞİLDİR?

JWT, “**JSON Web Token**” ifadesinin kısaltmasıdır; adından da anlaşılacağı gibi, kullanıcı doğrulama ve yetkilendirme süreçlerinde kullanılan **imzalanmış**, **taşınabilir** ve **durumsuz (stateless)** bir güvenlik bileşenidir.  
Bir başka deyişle: Oturum (session) bilgisini sunucuda değil, **kullanıcının taşıdığı imzalı bir JSON objesi** içinde barındırır. 🧳

JWT, üç ana bölümden oluşur:
| Bölüm | Görevi | Detaylı Açıklama |
|-------|--------|------------------|
| 🧩 **Header** | Token türü ve imzalama algoritmasını belirtir. | Genellikle `"alg": "HS256"`, `"typ": "JWT"` değerlerini içerir. |
| 💾 **Payload** | Kullanıcıya ait verileri (claims) taşır. | Örneğin kullanıcı ID’si, rolü, e-postası gibi bilgiler burada saklanır. |
| 🔏 **Signature** | Token’ın doğruluğunu garantiler. | Header + Payload verisi gizli anahtarla imzalanır, böylece değiştirilemez hale gelir. |

Örnek bir JWT yapısı:
Bu yapı, **nokta ile ayrılmış üç Base64 kodlu string**’den oluşur. Her biri, token’ın farklı katmanını temsil eder.

---

#### 💬 AMAÇ: “BİR KEZ DOĞRULA, HER YERDE TANINSIN” 🎯

JWT’nin temel amacı, kullanıcıyı **bir kez doğrulayıp**, sonraki tüm isteklerde tekrar kimlik sormadan işlemi güvenle devam ettirmektir.  
Böylece her istekte “session id” taşımaya ya da sunucuda oturum yönetimi yapmaya gerek kalmaz.

- 🧭 **Durumsuz mimariyi mümkün kılar:** Sunucu artık kullanıcıyı hatırlamak zorunda değildir; doğrulama token üzerinden yapılır.
- ⚡ **Performansı artırır:** Session bilgisinin RAM veya cache üzerinde tutulmaması, sistem kaynaklarını rahatlatır.
- 🧩 **Dağıtık sistemlerde kolay entegrasyon sağlar:** Mikroservisler, merkezi bir session veritabanına ihtiyaç duymadan, JWT üzerinden birbirini tanır.

---

#### 🧩 KULLANILMAZSA NE OLUR? ⚠️

Eğer JWT kullanılmazsa sistem, **session-based authentication** modeline döner.  
Bu modelde her kullanıcı için sunucuda bir session nesnesi oluşturulur ve takip edilir.

- 💾 **Bellek yükü artar:** Her kullanıcı için oturum saklandığından, yüksek trafikte sunucu kaynakları hızla tükenir.
- 🌐 **Dağıtık yapılar zayıflar:** Birden fazla sunucu olduğunda session bilgisini paylaşmak gerekir, bu da “session replication” maliyetini getirir.
- 🚪 **Ölçeklenebilirlik düşer:** Uygulamanın birden fazla node’da çalışması zorlaşır, çünkü kullanıcı bilgileri merkezi bir yere bağlı kalır.

Sonuç olarak: JWT olmadan sistem, **tek bir makineye mahkûm**, **güvenlikte merkezi**, **esneklikten uzak** hale gelir.

---

#### 🎯 ANA AMACI: GÜVENLİKTE MERKEZİYETİ ORTADAN KALDIRMAK 🧠

JWT’nin felsefesi, **“her token kendi güvenliğini taşır”** prensibine dayanır.  
Yani bir token, kendi içinde kimliği, rolü ve imzası olan **kendine yeten bir güvenlik kimliğidir.**

- 🪪 Token’ın kimlik bilgisi (claims) içinde saklanır.
- 🔐 Token, gizli anahtar ile imzalanarak değiştirilemez hale getirilir.
- ⏰ Token’a belirli bir “expiration time” verilir; bu süre bittiğinde kimlik geçersiz sayılır.
- 🧩 Her istekte header içinde taşınarak (`Authorization: Bearer <token>`) kimlik doğrulaması yapılır.

Bu sayede sistemin hiçbir bileşeni, diğerine “sen kimsin?” diye sormak zorunda kalmaz — çünkü **cevap zaten token’ın içindedir.**

---

#### 🚀 NE KATAR? GELİŞTİRİCİYE VE YAZILIMA ETKİLERİ 💼

JWT, yalnızca bir doğrulama mekanizması değildir; aynı zamanda geliştiriciye **disiplin, mimari vizyon ve sade güvenlik yönetimi** kazandırır.

- 🌍 **Mikroservis mimarisine uyum:**  
  Her servis kendi başına JWT doğrulaması yapabilir, merkezi session bağımlılığı olmadan çalışır.

- 💡 **Frontend–Backend ayrımı:**  
  React, Angular veya mobil istemciler backend ile güvenli iletişimi yalnızca token üzerinden yürütür.

- 🔄 **API güvenliği:**  
  Her API çağrısı bağımsız olarak doğrulanır; kullanıcı bir token olmadan hiçbir endpoint’e erişemez.

- 🧠 **Yazılım kültürü:**  
  JWT kullanmak, geliştiricinin “güvenlik katmanını düşünerek kod yazma” refleksini güçlendirir.  
  Artık sadece “veri dönen bir servis” değil, “güvenli veri dönen bir servis” inşa edilir.

---

#### 🧩 AVANTAJLARI VE GÜÇLERİ ⚙️

| Özellik | Açıklama |
|----------|-----------|
| 🌐 **Platform Bağımsızlık** | JSON formatı sayesinde her dilde (Java, JS, Python vb.) kolayca kullanılabilir. |
| ⚡ **Yüksek Performans** | Session saklanmadığı için, server-side bellek yükü ortadan kalkar. |
| 🔒 **Güvenli İmza Mekanizması** | Token içeriği imzalıdır; gizli anahtar bilinmeden değiştirilemez. |
| 🧭 **Dağıtık Sistem Uyumlu** | Farklı servisler arasında kimlik doğrulama tutarlı hale gelir. |
| 🧰 **Esnek Veri Taşınabilirliği** | Kullanıcı bilgileri, rol, e-posta veya izin detayları token içinde taşınabilir. |

---

#### ⚠️ DEZAVANTAJLARI VE ZAYIF NOKTALARI 🧨

| Zorluk | Açıklama |
|---------|-----------|
| 🕓 **Token İptal Edilemezliği** | Bir token süresi dolmadan iptal edilemez; bu da güvenlik riskini artırabilir. |
| 🧾 **Boyut Fazlalığı** | İçinde fazla bilgi taşındığında her istekte bant genişliği tüketir. |
| 🕵️‍♂️ **Sızma Riski** | Eğer token ele geçirilirse, saldırgan token süresi dolana kadar erişim sağlayabilir. |
| 🔄 **Yenileme Yönetimi (Refresh Token)** | Access token kısa ömürlü olmalı; aksi halde refresh token mantığı doğru uygulanmazsa güvenlik açıkları oluşabilir. |

---

#### 💻 KODLA ANLATALIM — JWT ÜRETME VE DOĞRULAMA 🔐

```java
@Component
public class JwtService {

    private static final String SECRET_KEY = "MySecretKey12345";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 saat geçerli
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
```

---

#### 🤝 SPRING SECURITY + JWT — GÜVENLİK ORKESTRASYONUNUN KUSURSUZ UYUMU 🎭

Spring Security ve JWT bir araya geldiğinde, sistemde **merkezi güvenlik kontrolü ile taşınabilir kimlik doğrulama** el ele verir.  
Bu birleşim, her isteğin **kimden geldiğini**, **hangi yetkilere sahip olduğunu** ve **hangi kaynağa erişebileceğini** otomatik olarak denetleyen bir orkestrasyon oluşturur.  
Kısacası Spring Security, güvenlik senfonisinin **şefi**, JWT ise **notaları taşıyan elçi** gibidir. 🎶

- 🧱 **Spring Security**, uygulamaya gelen her HTTP isteğini bir dizi filtre zincirinden geçirerek kontrol eder; yani “kimin hangi kapıdan gireceğine” karar verir.
- 🔐 **JWT** ise o kapıdan geçmeye çalışan kişinin **kim olduğunu kanıtlayan dijital kimlik kartıdır**; içindeki imzalanmış bilgiler sayesinde, sunucu kullanıcıyı ek sorguya gerek kalmadan tanır.
- ⚙️ Bu iki yapı birleştiğinde sistem, **state tutmadan (stateless)** güvenliği sağlar; yani kimlik doğrulama verileri artık oturumda değil, token içinde taşınır.

---

#### 🧩 BİLEŞENLERİN BİRLİKTE ÇALIŞMASI

| Bileşen | Görevi | Derin Açıklama |
|----------|---------|----------------|
| 🔑 **AuthenticationFilter** | Gelen istekteki JWT’yi yakalar ve çözümler. | Her istek geldiğinde header’daki “Bearer” token’ı alır, içindeki kullanıcı bilgisini (`subject`) çıkarır, doğrulama başarılıysa bu kullanıcıyı Spring Security’nin `SecurityContext`’ine ekler. Böylece sistem, sonraki katmanlarda o isteğin kimden geldiğini bilir. |
| 🧱 **SecurityConfig** | Güvenlik duvarını ve erişim politikalarını belirler. | Hangi endpoint’in herkese açık (`permitAll`), hangisinin korumalı (`authenticated`) olduğunu tanımlar. `SessionCreationPolicy.STATELESS` ayarıyla sistemin durumsuz çalışmasını garanti eder. |
| 🧩 **JwtUtils (ya da JwtService)** | Token üretir, çözer ve doğrular. | Gizli bir `SECRET_KEY` kullanarak token’ı imzalar, süre bitimini (`expiration`) ve kullanıcı kimliğini kontrol eder. Bu sınıf, token’ın sahte olup olmadığını anlamanın kalbidir. |
| 👤 **UserDetailsService** | Kullanıcıyı veri tabanından çeker ve kimliğini yükler. | Token içindeki kullanıcı adıyla DB’den ilgili kullanıcıyı bulur, rollerini (`GrantedAuthority`) getirir ve doğrulama sürecine dahil eder. Böylece yalnızca geçerli kullanıcılar erişim kazanır. |

---

#### 🧠 ÖZETLE:
Spring Security güvenliği **yönetir**, JWT ise güvenliği **taşır.**  
Birlikte kullanıldıklarında sistem, ne sadece hızlı ne de sadece güvenli olur — **her iki özelliği birden** kazanır.  
Artık her istek, arkasında imzalı bir kimlik beyanı taşır; bu da güvenliğin “kodla değil, mimariyle sağlandığı” anlamına gelir. 🛡️

---

```
                       🔐 JWT (JSON WEB TOKEN) — GÜVENLİK MİMARİSİ ŞEMASI 🔄
──────────────────────────────────────────────────────────────────────────────────────────
🌍 CLIENT (React / Angular / Mobil)               
   │
   │   🔑 LOGIN → POST /api/auth/login { username, password }
   ▼
══════════════════════════════════════════════════════════════════════════════════════════
🧠 SPRING SECURITY + JWT AKIŞ ZİNCİRİ
──────────────────────────────────────────────────────────────────────────────────────────
[1️⃣ Controller/AuthEndpoint] ─► [2️⃣ AuthenticationManager] ─► [3️⃣ AuthenticationProvider]
     │                                   │                                   │
     │                                   │                                   ▼
     │                                   │                         [4️⃣ UserDetailsService]
     │                                   │                           → DB'den kullanıcı bilgisi çekilir  
     │                                   ▼
     │                        [5️⃣ PasswordEncoder (BCrypt/Argon2)] 🔐
     │                                   │
     ▼                                   ▼
[✅ Authentication SUCCESS] ──────────────────────────────────────────────────────────────┐
                                                                                          │
                                                                                          ▼
══════════════════════════════════════════════════════════════════════════════════════════
🧩 JWT TOKEN ÜRETİM AŞAMASI (Stateless Kimlik)  
──────────────────────────────────────────────────────────────────────────────────────────
HEADER  ─┬─ alg: HS256       →  İmzalama algoritması
         └─ typ: JWT         →  Token tipi (JSON Web Token)
PAYLOAD ─┬─ sub: user@example.com → Kullanıcı kimliği (Subject)
         ├─ roles: [“ADMIN”, “USER”]  → Yetkiler
         ├─ iat: 1731066500           → Oluşturulma zamanı
         └─ exp: 1731070100           → Token geçerlilik süresi
SIGNATURE ── HMACSHA256( base64UrlEncode(header) + "." + base64UrlEncode(payload), SECRET_KEY )
══════════════════════════════════════════════════════════════════════════════════════════
📦 OLUŞAN TOKEN:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwi
cm9sZXMiOlsiQURNSU4iXSwiaWF0IjoxNzMxMDY2NTAwLCJleHAiOjE3MzEwNzAxMDB9.s0f9f9iAJQ9hL3B2bFtfCzpjM
══════════════════════════════════════════════════════════════════════════════════════════
                                                                                          │
                                                                                          ▼
[ CLIENT ]  ←─── JWT TOKEN geri döner  ───  🌍 Token localStorage / cookie içinde saklanır  
──────────────────────────────────────────────────────────────────────────────────────────
          🔁 ARTIK HER REQUEST'TE:
          Authorization: Bearer <JWT_TOKEN>
──────────────────────────────────────────────────────────────────────────────────────────
                                                                                          │
                                                                                          ▼
══════════════════════════════════════════════════════════════════════════════════════════
🔰 JWT DOĞRULAMA (Verification) SÜRECİ — Request Tarafı  
──────────────────────────────────────────────────────────────────────────────────────────
[6️⃣ JwtAuthFilter] ─► Token'ı header'dan alır → “Bearer” ön ekini temizler → içeriği çözer 🧩  
       │
       ▼
[7️⃣ JwtUtils / JwtService] ─► Signature doğrulaması yapılır 🔏  
       │   → SECRET_KEY ile HMACSHA256 kontrol edilir  
       │   → Expiration süresi dolmuş mu kontrol edilir ⏰  
       ▼
[8️⃣ UserDetailsService] ─► Token’daki `sub` (kullanıcı) ile DB’den UserDetails alınır  
       ▼
[9️⃣ SecurityContextHolder] ─► Authentication objesi oluşturulur → Context’e eklenir  
       ▼
[🔓 Authorization Layer] ─► Rol & Yetki kontrolü yapılır (`hasRole('ADMIN')`)  
       ▼
[✅ Controller / Service] ─► Artık güvenli erişim sağlanır → İş mantığı çalışır  
       ▼
[HTTP RESPONSE → 200 OK] → Gerekirse yeni JWT üretilir (refresh token) ♻️  
══════════════════════════════════════════════════════════════════════════════════════════

📘 ÖZET AKIŞ:
LOGIN → JWT oluşturulur → Client token’ı saklar → Her istekte header’da taşır →  
Filter → JwtUtils → SecurityContext → Authorization → Controller → Response  

──────────────────────────────────────────────────────────────────────────────────────────
🧠 ANA PRENSİP:
- Sunucu **session tutmaz** → sistem tamamen **stateless** çalışır.  
- Her istek kendi kimlik bilgilerini taşır.  
- Token sahteyse ❌ reddedilir, geçerliyse ✅ erişim sağlanır.  
──────────────────────────────────────────────────────────────────────────────────────────
⚙️ ÖNEMLİ SINIFLAR:
- `JwtAuthFilter` → Header’daki token’ı yakalayıp doğrulama başlatır  
- `JwtUtils` / `JwtService` → Token üretir, süresini ve imzasını kontrol eder  
- `SecurityConfig` → Filtre zinciri, izin politikaları ve stateless ayarları içerir  
- `UserDetailsService` → Kullanıcıyı veri tabanından yükler  
- `SecurityContextHolder` → Doğrulanmış kimliği thread-safe şekilde taşır  
──────────────────────────────────────────────────────────────────────────────────────────
```

---

#### 💻 ÖRNEK KOD — SPRING SECURITY + JWT ENTEGRASYONU

```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
```