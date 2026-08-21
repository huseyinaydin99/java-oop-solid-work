### 🔌 Adapter Pattern Nedir?

Adapter Pattern için dış servisten veri çekme senaryosu oldukça iyi bir örnek olur. Buradaki temel fikir: uygulamamızın beklediği arayüz ile dış servisin sunduğu arayüz farklıysa, araya bir Adapter koyarak iki yapıyı birbirine uyumlu hâle getirmek.
Adapter Pattern, birbirleriyle uyumsuz arayüzlere sahip iki yapının birlikte çalışmasını sağlayan yapısal (Structural) bir tasarım desenidir. Mevcut sınıfı değiştirmek yerine araya Adapter koyarak onun sunduğu davranışı uygulamanın beklediği arayüze dönüştürür.

### 🚫 Adapter Pattern Ne Değildir?

Adapter, mevcut bir sınıfın davranışını yeniden tasarlayan veya ona yeni sorumluluklar ekleyen bir yapı değildir. Temel görevi yeni bir davranış üretmek değil, mevcut davranışı farklı bir arayüz üzerinden erişilebilir hâle getirmektir.

### 🎯 Hangi Amaca Hizmet Eder?

Temel amacı, uyumsuz arayüzler arasındaki iletişimi sağlayarak istemciyi uyarlanan sınıfın detaylarından ayırmaktır. Böylece uygulama, kullandığı dış servisin veya mevcut bileşenin API'sine doğrudan bağımlı kalmadan kendi beklediği sözleşme üzerinden çalışır.

### 🛠️ Hangi Sorunsalları Çözer?

Farklı isimlendirme, metod imzası veya veri modeli kullanan bileşenlerin doğrudan birbirine bağlanmasından kaynaklanan uyumsuzluk problemini çözer. Özellikle üçüncü parti kütüphaneler, dış servisler veya değiştirilmesi mümkün olmayan legacy kodlarla entegrasyonda mevcut kodu bozmadan uyum sağlar.

#### 1. Uygulamanın beklediği arayüz

```java
public interface UserService {
    User getUser(Long id);
}
```

UserService, uygulamanın dış dünyadan bağımsız olarak kullandığı sözleşmedir. Uygulama artık dış servisin nasıl çalıştığını değil, yalnızca getUser() üzerinden kullanıcı alabildiğini bilir.

#### 2. Uygulamanın kendi modeli

```java
public record User(
    Long id,
    String name,
    String email
) {
}
```

User, uygulamanın ihtiyaç duyduğu sade veri modelidir. Dış servisin response yapısını doğrudan kullanmak yerine kendi modelimizi kullanarak uygulama ile dış servis arasındaki bağı azaltıyoruz.

#### 3. Dış servisin yapısı

```java
public class ExternalUserClient {
    public ExternalUserResponse fetchUser(Long userId) {
        // Gerçekte HTTP isteği yapılır.
        return new ExternalUserResponse(
                userId,
                "Hüseyin",
                "huseyinaydin99@gmail.com"
        );
    }
}
```

ExternalUserClient, bizim kontrolümüzde olmayan bir dış servisi temsil ediyor ve kendi metodunu (fetchUser) sunuyor. Buradaki problem, uygulamamızın beklediği UserService sözleşmesiyle bu API'nin doğrudan uyuşmamasıdır.

#### 4. Dış servisin response modeli

```java
public record ExternalUserResponse(
    Long userId,
    String fullName,
    String emailAddress
) {
}
```

Dış servis kullanıcı bilgisini userId, fullName ve emailAddress alanlarıyla döndürüyor. Bu model uygulamanın User modeliyle aynı olmadığı için iki model arasında dönüşüm yapılması gerekiyor.

#### 5. Adapter

```java
public class ExternalUserAdapter implements UserService {

    private final ExternalUserClient client;

    public ExternalUserAdapter(ExternalUserClient client) {
        this.client = client;
    }

    @Override
    public User getUser(Long id) {
        ExternalUserResponse response = client.fetchUser(id);

        return new User(
                response.userId(),
                response.fullName(),
                response.emailAddress()
        );
    }
}
```

ExternalUserAdapter, uygulamanın beklediği UserService arayüzünü uygularken dış servisin ExternalUserClient yapısını içeride kullanıyor. Böylece dış servisin farklı metod ve model yapısını uygulamanın anlayacağı User modeline dönüştürerek iki sistemi birbirinden ayırdım.

#### 6. Kullanım

```java
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User getUser(Long id) {
        return userService.getUser(id);
    }
}
```

UserController, dış servisin varlığından tamamen habersiz şekilde yalnızca UserService ile çalışıyor. Yarın ExternalUserClient yerine başka bir servis kullanılsa bile Controller değişmeden kalabilir; değişiklik Adapter katmanında karşılanır.

### Şematize;

```text
                         UYGULAMA
                            │
                            │ UserService
                            ▼
                  ┌─────────────────────┐
                  │   UserController    │
                  └──────────┬──────────┘
                             │
                             ▼
                  ┌─────────────────────┐
                  │    UserService      │
                  │    «interface»      │
                  └──────────┬──────────┘
                             │
                             │ implements
                             ▼
              ┌─────────────────────────────┐
              │    ExternalUserAdapter      │
              │         «Adapter»           │
              ├─────────────────────────────┤
              │ + getUser(Long id) : User   │
              └──────────────┬──────────────┘
                             │
                             │ delegates
                             ▼
              ┌─────────────────────────────┐
              │     ExternalUserClient      │
              │        «Adaptee»             │
              ├─────────────────────────────┤
              │ + fetchUser(Long id)        │
              └──────────────┬──────────────┘
                             │
                             │ HTTP / API
                             ▼
                    ┌─────────────────┐
                    │   DIŞ SERVİS    │
                    └────────┬────────┘
                             │
                             │ ExternalUserResponse
                             ▼
              ┌─────────────────────────────┐
              │    ExternalUserAdapter      │
              │                             │
              │ ExternalUserResponse        │
              │            ↓                │
              │          User               │
              └──────────────┬──────────────┘
                             │
                             │ User
                             ▼
                         UYGULAMA
```

Kısacası: Adapter, uyumsuz iki arayüz arasında dönüştürücü görevi görür. Uygulamayı dış servisin API'sine doğrudan bağlamak yerine, dış servisi kendi UserService sözleşmem üzerinden kullandım.

---

Adapter Pattern'in özü tam olarak şu:

🔌 Dış servisin değiştiremeyeceğimiz ExternalUserResponse yapısını olduğu gibi kabul edip, bir Adapter sınıfı aracılığıyla bu cevabı kendi User modelimize eşledik; böylece mevcut proje kodlarımızı değiştirmeden dış yapıya uyum sağladık, zaten dış servisin kodlarını değiştiremeyiz!

```text
Dış Servis
    │
    ▼
ExternalUserResponse
    │
    │  Adapter
    │  eşleme / uyarlama
    ▼
User
    │
    ▼
Mevcut Uygulama
```

>Asıl fikir: Dışarıdaki yapıyı değiştirmedim, kendi uygulamamı da ona göre bozmadım; araya Adapter koyup ikisi arasındaki uyumsuzluğu çözdüm.