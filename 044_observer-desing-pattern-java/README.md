```text
                    ┌──────────────────────┐
                    │        Order         │
                    │       Subject        │
                    └──────────┬───────────┘
                               │
                     status değişti
                               │
                  ┌────────────┴────────────┐
                  │      notifyObservers()  │
                  └────────────┬────────────┘
                               │
                    ┌──────────┴──────────┐
                    │                     │
                    ▼                     ▼
          ┌─────────────────┐   ┌─────────────────┐
          │EmailNotification│   │ SmsNotification │
          │   Observer      │   │    Observer     │
          └─────────────────┘   └─────────────────┘
```

### Observer Tasarım Şablonu nedir?

Observer Pattern, bir nesnede bir değişiklik olduğunda, bu değişikliği takip eden diğer nesnelerin otomatik olarak haberdar edilmesini sağlayan bir tasarım kalıbıdır. Yani ben bir nesneyi gözlemci olarak kaydederim; o nesnede bir değişiklik olduğunda sistem bana tek tek haber verir ve ben de gereken işlemi yaparım.

### Hangi soruna çözüm getirir?

Observer Pattern, bir nesnede meydana gelen değişiklikleri başka nesnelere tek tek haber verme ve bu nesnelerle sıkı bağımlılık kurma sorununu çözer. Ben bir nesnenin değişikliklerini takip etmek isteyen yapıları gözlemci olarak kaydederim; nesne değiştiğinde de hepsini kendim tek tek çağırmak yerine sistem otomatik olarak haberdar eder.

### Nesnelerle sıkı bağımlılık nedir?

Sıkı bağımlılık, bir sınıfın başka sınıfların nasıl çalıştığını ve hangi sınıfların var olduğunu doğrudan bilmek zorunda kalmasıdır. Örneğin Order sınıfının hem EmailNotification hem de SmsNotification sınıfını doğrudan oluşturup çağırması, bu sınıflara sıkı bağımlı olduğu anlamına gelir.

### Örneğimizde ne yaptık?

Örneğimizde Order sınıfını EmailNotification ve SmsNotification sınıflarına doğrudan bağlamak yerine, ikisini de OrderObserver üzerinden gözlemci olarak kaydettik. Böylece Order sadece “durumum değişti, gözlemcileri haberdar et” dedi; e-posta veya SMS'in nasıl gönderildiğini bilmedi.