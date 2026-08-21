### Factory Method Desing Pattern nedir?

Factory Method, nesnenin hangi somut sınıftan üretileceği kararını doğrudan new ile uğraşan istemci kodundan çıkarıp, alt sınıflara bırakarak nesne oluşturma sürecini esnek ve genişletilebilir hâle getirir.

Burada sistemimiz EmailNotification veya SmsNotification oluşturabiliyor; asıl kullanım kodu ise hangi somut bildirimin üretildiğini bilmek zorunda kalmıyor.

```java
public interface Notification {
    void send(String message);
}
```

Notification, tüm bildirim türlerinin uyması gereken ortak sözleşmeyi tanımlar; böylece istemci kodu Email veya SMS gibi ayrıntılara bağımlı kalmaz.
Bu yapı Dependency Inversion açısından da daha sağlıklıdır çünkü üst seviye kod somut sınıflara değil, soyutlamaya dayanır.

```java
public final class EmailNotification implements Notification {


    @Override
    public void send(String message) {
        System.out.println("E-posta gönderildi: " + message);
    }
}
```

EmailNotification, Notification sözleşmesini gerçekleştirerek e-posta gönderme davranışını kendi sorumluluğunda tutar.
İstemci bu sınıfın iç çalışma detaylarını bilmez; yalnızca Notification üzerinden davranışı kullanır.

```java
public final class SmsNotification implements Notification {


    @Override
    public void send(String message) {
        System.out.println("SMS gönderildi: " + message);
    }
}
```

SmsNotification da aynı sözleşmeyi uygulayarak SMS gönderme davranışını kapsüller ve yeni bir bildirim türünün mevcut yapıyı bozmasını engeller.
Böylece sistem Open/Closed Principle doğrultusunda yeni bildirim türleri eklenmesine açık, mevcut kodun değiştirilmesine ise mümkün olduğunca kapalı hâle gelir.

Factory Method burada devreye giriyor

```java
public abstract class NotificationService {


    public void notify(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }


    protected abstract Notification createNotification();
}
```

NotificationService, bildirimin nasıl gönderileceğini bilir ancak hangi Notification nesnesinin oluşturulacağını bilmez; bu kararı createNotification() adlı Factory Method'a bırakır.
Böylece nesne oluşturma sorumluluğu kullanım mantığından ayrılır ve üst sınıf değişmeden farklı üretim stratejileri tanımlanabilir.

```java
public final class EmailNotificationService extends NotificationService {


    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
```

EmailNotificationService, Factory Method'u override ederek üretilecek somut nesnenin EmailNotification olduğunu belirler.
Yani NotificationService akışı yönetirken, hangi nesnenin üretileceğine alt sınıf karar verir. Buradaki alt sınıf, NotificationService sınıfından extends eden sınıftır.

```java
public final class SmsNotificationService extends NotificationService {


    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
```

SmsNotificationService aynı Factory Method'u kullanarak bu kez SmsNotification üretir ve ortak iş akışını değiştirmeden farklı bir ürün sağlar.
Yeni bir PushNotificationService eklemek istediğimizde mevcut servis mantığına dokunmadan yalnızca yeni bir Notification ve onu üreten servis oluşturabiliriz.

```java
public class Main {


    public static void main(String[] args) {
        NotificationService emailService =
                new EmailNotificationService();


        NotificationService smsService =
                new SmsNotificationService();


        emailService.notify("Hesabınıza giriş yapıldı.");
        smsService.notify("Doğrulama kodunuz: 123456");
    }
}
```

İstemci kodu notify() üzerinden ortak iş akışını çalıştırır ve hangi somut Notification nesnesinin oluşturulduğuyla ilgilenmez.
Factory Method'un temel amacı tam olarak budur: nesne oluşturma kararını alt sınıflara bırakarak üretim sürecini kullanım mantığından ayırmak.