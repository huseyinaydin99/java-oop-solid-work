#### 🛡️ Spring Security, JWT ve Angular ile Kimlik Doğrulama Yapım
#### 🔍 1. Yazdığım Kodun Genel Fotoğrafı

Bu projede aslında üç katmanlı bir güvenlik zinciri kurmuş oluyorum: Spring Security + JWT backend tarafında, Angular + HTTP Interceptor + Token Storage da frontend tarafında, böylece kullanıcı giriş yaptıktan sonra tüm isteklerimde “ben kimim, rolüm ne, bu endpoint’e erişmeye hakkım var mı?” sorularını otomatik ve güvenli şekilde cevaplayabilen bir sistem tasarlamış oluyorum. 💫

AuthService, login ve register metotlarıyla Spring Boot tarafındaki http://localhost:8080/api/auth/signin ve signup endpoint’lerine JSON gövdeli istekler gönderiyor ve bu isteklerden dönen JWT’yi alıp uygulamanın geri kalanında kullanacağım ham kimlik doğrulama verisi olarak kullanmamı sağlıyor, böylece login/register işlemini bileşenlerden ayırıp tek bir servis üzerinden yönetmiş oluyorum. 🔑

TokenStorageService, tarayıcıdaki sessionStorage üzerinde auth-token ve auth-user anahtarlarıyla hem token’ı hem de kullanıcı bilgilerini (özellikle roller) saklayarak, sayfa yenilense bile oturum bilgisinin kaybolmamasını sağlıyor; ayrıca saveToken, saveUser, getToken, getUser gibi metotlarla bu depolama detayını Angular bileşenlerinden gizleyip, component’lerin sadece “oturum var mı, kullanıcı kim, rolleri ne?” sorularına odaklanmasına izin veriyor. 📦

AuthInterceptor sınıfı, Angular’ın HttpInterceptor mekanizmasını kullanarak her çıkış HTTP isteğini dinliyor, eğer TokenStorageService içinde token bulursa isteği req.clone() ile klonlayıp header’a token’ı ekliyor; Spring Boot ile çalışırken buradaki yorum satırı olan Authorization: Bearer <token> başlığını aktif ederek JWT’yi backend’e standart şekilde göndermiş ve güvenlik zincirini tamamlamış oluyorum. 🚇

UserService, backend’deki http://localhost:8080/api/test/ altındaki all, user, mod, admin endpoint’lerine istek atarak rol bazlı içerikleri çekiyor; bu sayede kullanıcı login olduktan sonra rolüne göre farklı “board” bileşenleri (admin, moderator, user) için içerik sağlayan sade ama güçlü bir API katmanı ortaya çıkıyor. 📡

HomeComponent, BoardUserComponent, BoardModeratorComponent, BoardAdminComponent gibi bileşenler ise UserService üzerinden gelen metin içerikleri ekrana basıp, hata durumunda backend’in gönderdiği message alanını yakalayarak kullanıcıya anlamlı geri bildirim veriyor; LoginComponent ve RegisterComponent ise form validasyonları, hata mesajları ve başarı durumları ile uçtan uca kullanıcı deneyimini tamamlıyor. 🧩

#### 🧱 2. Spring Security — Nedir, Ne Değildir?
#### 🎯 Amacı ve Felsefesi

Spring Security, Java dünyasında kimlik doğrulama ve yetkilendirme işini uygulamanın geri kalanından soyutlayarak merkezi bir güvenlik katmanı oluşturmamı sağlayan, esnek ve genişletilebilir bir güvenlik çerçevesidir; ben iş mantığıma odaklanırken, “bu isteği kim attı, yetkisi var mı, bu endpoint sadece admin’e mi açık olmalı?” gibi soruların ağırlığını framework’ün sırtına bırakırım. 🛡️

Bu yapı, sadece basit bir “login formu kütüphanesi” değildir; filtre zincirleri, SecurityContext, rol tabanlı erişim kontrolleri, method-level security (@PreAuthorize, @Secured) ve JWT, OAuth2 gibi mekanizmalarla entegre olabilen, neredeyse tüm modern güvenlik ihtiyaçlarını karşılayabilecek kadar kapsamlı bir altyapı sunar. 🌐

#### ⚖️ Kullanılmazsa Ne Olur?

Uygulamada Spring Security kullanmazsam, her controller’da manuel olarak “bu isteği atan kullanıcı kim, oturum geçerli mi, rolü uygun mu?” kontrollerini kendim yazmak zorunda kalır, bu kontrolleri her yerde tekrarlar, zamanla kopyala–yapıştır kodlarla dolu, bakımı zor ve güvenlik açıklarına son derece müsait bir yapı üretmiş olurum. 🧨

Güvenlik kodunu kendim yazdığımda, CSRF koruması, session fixation, brute force saldırılarına karşı koruma, parola saklama politikaları gibi kritik konuları ya gözden kaçırma riskim artar ya da her birini sıfırdan tasarlayıp test etmek için ciddi zaman harcamam gerekir; Spring Security yoksa bu alanlarda “standart, battle-tested” bir kalkanım da kalmamış olur. ⚔️

#### 🧰 Özellikleri, Avantajları ve Dezavantajları

#### Özellikler:

Spring Security, FilterChain tabanlı mimarisiyle gelen her HTTP isteğini bir dizi filtreden geçirir; bu filtreler sayesinde kimlik doğrulama, JWT okumaları, yetki kontrolleri, exception handling gibi süreçler request pipeline’ına düzgünce oturur ve bu zinciri konfigürasyon dosyaları veya Java konfigürasyon sınıfları üzerinden oldukça detaylı biçimde yönetebilirim. 🧵

#### Avantajlar:

Framework, rol tabanlı (RBAC) veya yetki bazlı (authority-based) erişim kontrolünü tek yerden yönetmemi sağlar; bu sayede “admin rolüne şu endpoint’ler açık olsun, user rolüne bunlar kapalı kalsın” gibi kuralları hem deklaratif anotasyonlarla hem de HTTP konfigürasyonuyla çok net bir şekilde ifade edebilir, mimarinin güvenlik kısmını da kodun geri kalanı kadar şeffaf ve okunaklı kılarım. ✅

Bir kez doğru kurguladığımda, yeni endpoint eklediğimde yapmam gereken şey çoğunlukla sadece “şu rolü iste” demekten ibaret olur; böylece ölçek büyüdükçe güvenliği unutma riskim azalır, proje büyürken güvenlik katmanım da kontrollü şekilde benimle birlikte büyür. 📈

#### Dezavantajlar:

Spring Security, kavramsal olarak zengin bir çerçeve olduğu için ilk temas ettiğimde filtre zinciri, konfigürasyon, JWT entegrasyonu, custom UserDetailsService gibi kavramlar biraz dik bir öğrenme eğrisi yaratabilir; dokümantasyon okumadan, temellerini sindirmeden ilerlersem “neden bu istek 403 dönüyor, neden login olmuyoruz?” gibi sorularla çok zaman kaybedebilirim. 🧩

Yanlış konfigüre edildiğinde, örneğin bazı endpoint’leri “permitAll” bırakıp unuttuğumda veya yanlış rol eşleştirmeleri yaptığımda, güvenlik açıkları da framework’ün kendisinden değil, benim konfigürasyon hatamdan kaynaklanabilir; bu yüzden Spring Security’yi kullanmak kadar, onu doğru ve bütüncül düşünmek de önemlidir. 🧠

#### 🔑 3. JWT (JSON Web Token) — Ne İşe Yarar, Kullanılmazsa Ne Eksik Kalır?
#### 🎯 JWT’nin Amacı

JWT, kullanıcı bir kez kimliğini kanıtladıktan (örneğin Spring Security’nin login endpoint’ine doğru kullanıcı adı/şifre gönderdiğinde) sonra, backend’in bu kullanıcı için imzalı bir “jeton” üretmesini sağlayan, bu jetonun da daha sonra her istekte header üzerinden taşınarak kullanıcıyı tekrar tekrar tanıtmasını mümkün kılan stateless kimlik doğrulama mekanizmasıdır. 🎟️

Bu yapı, “server tarafında session tutma” yükünü ortadan kaldırarak her isteğin içinde gerekli kimlik bilgilerini taşıyan, imzalı ve manipüle edilmeye karşı korunan bir paket oluşturur; backend JWT’nin imzasını doğrulayıp içindeki claim’leri (rol, kullanıcı adı, expiry vs.) okuyarak karar verir ve bu sayede yatayda ölçeklenebilir, hafif ve dağıtık ortamlara uygun bir güvenlik yapısı ortaya çıkar. 🌍

#### ❌ JWT Kullanmazsam Ne Olur?

JWT kullanmaz, klasik session tabanlı yaklaşımı tercih edersem, sunucu tarafında session state tutmam gerekir; bu da load balancer arkasında birden fazla instance çalıştırdığımda session paylaşımı, session replicasyonu, sticky session gibi konularla uğraşmam anlamına gelir ve mimariyi sade tutmak istediğim modern mikroservis senaryolarında bana ekstra karmaşıklık yükler. 🧱

Her istekte kullanıcıyı tanımak için cookie tabanlı session id kullanırken, cross-domain, CORS ve mobil istemcilerle entegrasyon gibi konularda ekstra dikkat etmem gerekir; JWT olmadan da çözülebilir elbette, fakat JWT bu alanlarda çok daha standart, taşınabilir ve API-odaklı bir yaklaşım sunarak işlerimi netleştirir. 📡

#### 🧰 JWT’nin Özellikleri, Avantajları, Dezavantajları

#### Özellikler:

JWT üç parçadan oluşur: header, payload ve signature; header ve payload base64 ile encode edilir, signature ise gizli bir anahtar ile imzalanır, böylece token’ın içeriği okunabilir olsa da (şifreli değil, encode’lu) imza sayesinde değiştirilip değiştirilmediği anlaşılır, yani token üzerinde oynama yapılırsa backend bunu hemen fark eder. 🔐

#### Avantajlar:

JWT stateless olduğu için backend üzerinde “kim hangi session’da, hangi node’da bağlı” gibi bilgileri tutmak zorunda kalmam; her istek kendi kimlik bilgisiyle geldiği için, bu yapıyı container’lar, Kubernetes pod’ları, serverless fonksiyonlar gibi dağıtık ortamlarda zorlanmadan kullanabilirim ve bu da sistemin ölçeklenebilirliğine doğrudan katkı sağlar. 🚀

Token’a kullanıcı rolleri, izinleri, ek claim’ler koyabildiğim için “bir istekte hem kimlik doğrulama hem de yetki bilgisi tek paketle taşınır” mantığıyla çalışır; böylece her istekte ekstra DB sorgusu atmak zorunda kalmadan, çoğu kararı sadece token içeriğini okuyarak verebilirim. 📦

#### Dezavantajlar:

JWT’nin en çok dikkat isteyen tarafı, iptal (revoke) ve süre yönetimidir; token’ı verdikten sonra süresi bitene kadar geçerlidir, bu nedenle yanlış token sızarsa veya kullanıcıyı sistem dışı bırakmak istersem black-list / refresh token gibi ek mekanizmalar kurmam gerekir, aksi halde token süresi bitene kadar geçerli kalmaya devam eder. ⏳

Token’ın payload kısmı şifreli olmadığı için (sadece encode) gizli verileri kesinlikle payload içine koymamam gerekir; yanlış tasarım yaparsam, istemcinin eline geçen token üzerinden gereğinden fazla bilgi sızdırabilir, saldırgana sistem hakkında fazladan içgörü vermiş olurum. 🕵️‍♂️

#### 🌀 4. Angular Tarafı — AuthInterceptor, Servisler ve Bileşenler

Burada yazdığım Angular kodu, Spring Security + JWT ile konuşabilen bir frontend güvenlik katmanı rolü görüyor; formlar, interceptor, token saklama ve rol bazlı sayfalar bir araya gelerek kullanıcıya uçtan uca bir deneyim sunuyor.

#### 🌉 4.1 AuthInterceptor — Her İsteğe Otomatik Token Enjeksiyonu

AuthInterceptor sınıfında HttpInterceptor arayüzünü implemente ederek Angular’ın tüm HTTP istek akışının arasına giriyorum; intercept metodu her istek öncesinde tetikleniyor, ben de burada TokenStorageService üzerinden daha önce login sırasında kaydettiğim token’ı getToken() ile çekip, eğer null değilse req.clone() ile yeni bir istek klonlayıp header’a token’ı ekliyorum. 🧬

Spring Boot backend’ine JWT gönderirken, yorum satırında bıraktığım satırda olduğu gibi Authorization header’ı altında Bearer <token> formatında gönderebilirim; şu an kodda x-access-token seçili olsa da bu satırı Spring Security ile entegrasyon için Authorization başlığını aktif ederek kullanmam, güvenlik zincirinin standartlaştırılmış şekilde tamamlanmasını sağlar. 🧾

authInterceptorProviders dizisinde HTTP_INTERCEPTORS sağlayıcısına multi: true ile interceptor’ü ekleyerek, Angular’ın tüm uygulama genelinde bu interceptor’ü kullanmasını sağlıyor, böylece tek satırlık manuel token ekleme kodu bile yazmadan her isteğin güvenli ve JWT’li gitmesini garanti altına alıyorum. 🛰️

#### 🔐 4.2 AuthService — Login ve Register Akışının Beyni

AuthService, HttpClient kullanarak AUTH_API sabiti ile http://localhost:8080/api/auth/ taban URL’ine istek atan, login ve register metotlarıyla kullanıcıdan form üzerinden aldığı username, password ve email bilgilerini JSON gövde olarak POST eden ve backend’den dönen JWT, roller ve kullanıcı metadatasını taşıyan cevabı observable olarak bileşenlere dönen bir servis katmanı görevi görüyor. 📬

httpOptions içinde Content-Type: application/json başlığını set ederek, backend’in body’yi JSON olarak parse etmesini sağlıyor, böylece Spring Boot tarafındaki @RequestBody ile DTO’lara rahatça map edilen temiz bir iletişim protokolü oluşturmuş oluyorum; bileşenler ise sadece authService.login(...).subscribe(...) diyerek işin ağ tarafıyla uğraşmadan iş akışına odaklanabiliyor. 🌐

#### 💾 4.3 TokenStorageService — Oturumun Hafızası

TokenStorageService, tarayıcının sessionStorage alanını kullanarak TOKEN_KEY ve USER_KEY sabitleriyle JWT ve kullanıcı bilgisini saklıyor; saveToken metodu önce eski token’ı siliyor sonra yenisini yazıyor, getToken ile okuyorum, aynı şekilde saveUser kullanıcı verisini JSON.stringify ederek kaydediyor, getUser ise null değilse JSON.parse edip bana JS nesnesi olarak geri döndürüyor. 📁

signOut() metodu ile window.sessionStorage.clear() çağrısı yaparak tüm session verilerini temizliyor, bu da logout işlemi için basit ama etkili bir yaklaşım sağlıyor; böylece “kullanıcı çıkış yaptıktan sonra token veya roller herhangi bir yerde kalmasın” kuralını çok net bir şekilde uygulamış oluyorum. 🚪

#### 📡 4.4 UserService — Roller İçin Ayrılmış Endpoint’lere Ulaşan Katman

UserService, API_URL olarak http://localhost:8080/api/test/ tabanını kullanıp getPublicContent, getUserBoard, getModeratorBoard, getAdminBoard metotlarıyla farklı roller için hazırlanmış endpoint’lere GET istekleri atıyor; responseType: 'text' diyerek backend’in döndürdüğü string içerikleri doğrudan alıp bileşenlere iletiyor ve bu bileşenler de gelen metni ekrana basıyor. 📜

Bu yapı sayesinde, frontend tarafında ayrı ayrı “admin sayfası, moderator sayfası, user sayfası” komponentleri kurup hepsini aynı servisten besleyebiliyorum; güvenlik kontrolü backend’de Spring Security + JWT ile yapıldığı için, Angular tarafı sadece gelen cevap başarılı mı, hata mı, hata ise mesaj ne gibi kullanıcıya yansıyan kısma odaklanıyor. 🎛️

#### 🧱 4.5 Login, Register, Profil ve Board Bileşenleri

#### LoginComponent & Şablonu:

Login formunda [(ngModel)] ile form.username ve form.password alanlarını çift yönlü bağlayıp, template-driven form kullanıyorum; form submit edildiğinde authService.login(...) çağrısı yapılıyor, başarılı cevap geldiğinde data.accessToken içindeki token saveToken ile kaydediliyor, aynı veri saveUser ile user objesi olarak saklanıyor, ardından isLoggedIn true olup roller çekiliyor ve reloadPage() ile sayfa yenilenerek guard’lar, navbar vs. yeni durumu okuyabiliyor. 🚪🔑

#### RegisterComponent & Şablonu:

Kayıt formunda username, email ve password alanlarını yine [(ngModel)] ile form objesine bağlıyorum, Angular’ın yerleşik validator’ları (required, minlength, maxlength, email) ile kullanıcıya anında geri bildirim veren bir validasyon katmanı kuruyorum; submit edildiğinde authService.register(...) çağırılıyor, başarılı olursa isSuccessful true, hata olursa isSignUpFailed true ve errorMessage doluyor, böylece kullanıcıya hem başarı hem hata akışları anlamlı mesajlarla gösteriliyor. 📝

#### ProfileComponent & Şablonu:

Profil sayfasında TokenStorageService.getUser() ile currentUser bilgisini alıyorum, token’ın bir kısmını (baş ve son 20 karakterini) görsel amaçlı ekranda göstererek hem güvenlik için tamamını sızdırmıyor hem de geliştirici olarak “gerçekten JWT geldi mi, hangi kullanıcıdayız?” sorularına pratik bir cevap üretiyorum; ayrıca email ve roller listesi ile oturumun kim adına açıldığını net şekilde görmüş oluyorum. 🧑‍💻

#### BoardUser / BoardModerator / BoardAdmin / Home:

Bu bileşenler UserService üzerinden ilgili board metodunu çağırıyor, cevap gelince content değişkenine atıyor, hata durumunda err.error içindeki JSON’dan message alanını çıkarıp kullanıcıya gösteriyor; böylece hem yetkisi olan kullanıcı doğru mesajı görüyor, hem de yetkisiz erişim durumunda backend’in döndürdüğü anlamlı hata mesajı frontende kadar taşınıyor. 📢

#### 🤝 5. Spring Security + JWT + Angular Bir Arada Nasıl Çalışıyor?

#### Aşağıda kurduğum akışı tek bir senaryo üzerinden okuyabilirim:

#### Kullanıcı Login Olur 🔐

Kullanıcı Angular’daki login formuna username ve password girer, LoginComponent bu veriyi alıp AuthService.login(username, password) çağrısını yapar; bu çağrı Spring Boot tarafındaki /api/auth/signin endpoint’ine gider, Spring Security kimlik doğrulamasını yapar, kullanıcı doğruysa JWT üretip JSON cevap içinde (örneğin accessToken, roles gibi alanlarla) geri döner.

#### JWT Tarayıcıya Kaydedilir 💾

Angular tarafında next bloğunda this.tokenStorage.saveToken(data.accessToken) ve this.tokenStorage.saveUser(data) çağrılarıyla token ve kullanıcı bilgisi sessionStorage içine yazılır; böylece sayfa yenilense bile bu bilgiler oturum kapatılana kadar elde tutulur, uygulama kullanıcıyı “hatırlar”.

#### Her İstekte Interceptor Devreye Girer 🚇

Kullanıcı daha sonra UserService.getAdminBoard() gibi bir çağrı yaptığında, bu istek backend’e gönderilmeden hemen önce AuthInterceptor.intercept metodu devreye girer; interceptor TokenStorageService.getToken() ile token’ı okuyup, varsa isteği klonlar ve header’a JWT’yi (Spring için Authorization: Bearer <token>) ekleyerek Spring Security’nin anlayacağı formatta sunucuya yollar.

#### Spring Security + JWT Filtreleri Doğrulama Yapar 🛡️

Backend tarafında Spring Security, JWT filtre zinciri içinde header’daki token’ı okur, imzayı doğrular, süresini ve içindeki claim’leri kontrol eder; token geçerliyse SecurityContext içine kullanıcı kimliğini ve rollerini yerleştirir, böylece /api/test/admin gibi bir endpoint’e gelen istekte “bu kullanıcı admin mi, değil mi?” sorusu basta JWT üzerinden cevaplanır.

#### Rol Bazlı İçerik ve Hata Yönetimi 🎯

Eğer kullanıcı gerçekten admin ise Spring Security isteğe izin verir, controller ilgili içeriği üretip Angular’a döner; Angular bileşenleri content alanına bu metni basar. Yetkisizse Spring Security 403 veya uygun bir hata kodu ile beraber bir JSON mesajı döner, Angular tarafında hata bloğunda JSON.parse(err.error).message ile bu mesaj okunur ve kullanıcıya gösterilir, böylece güvenli ama kullanıcıya da anlaşılır bir deneyim sunmuş olurum.

#### 🧩 6. Bu Mimari Bana Ne Katar? (Geliştirici Gözüyle)

Spring Security + JWT + Angular kombinasyonu sayesinde, kimlik doğrulama ve yetkilendirme işini hem backend’de hem frontend’de katmanlı, tekrar kullanılabilir ve test edilebilir bir yapıda kurmuş oluyorum; bu yaklaşım, yazılımcı olarak bana “güvenlik kodu ile iş mantığını birbirinden ayırma, rolleri merkezi bir yerden yönetme ve uygulamayı ölçeklenebilir bir mimariye taşıyabilme” özgürlüğü veriyor. 💼

JWT ile stateless bir güvenlik modeline geçtiğim için, ileride sistemi mikroservis mimarisine böldüğümde veya farklı istemcileri (mobil, SPA, üçüncü parti entegrasyonlar) bu API’ye bağlamak istediğimde, “herkes için aynı standardı kullanıyorum” rahatlığını hissediyorum; tek yapmam gereken, tüm istemcilerin her isteğe doğru header ile token’ı eklediğinden emin olmak oluyor. 🌉

Angular tarafındaki interceptor, servis ve bileşen ayrımı ise frontend kodumu temiz tutmamı sağlıyor; her bileşen sadece kendi ekranının form validasyonları, kullanıcıya gösterilen mesajlar ve routing gibi UI odaklı kısımlarla ilgilenirken, ağ iletişimi ve güvenlik entegrasyonu servis ve interceptor katmanlarında çözümlenmiş durumda kalıyor, bu da uzun vadede kodu okurken ve geliştirirken beni ciddi anlamda rahatlatıyor. 🧠✨