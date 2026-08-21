package tr.com.huseyinaydin;

import tr.com.huseyinaydin.email.EmailNotificationService;
import tr.com.huseyinaydin.notification.NotificationService;
import tr.com.huseyinaydin.sms.SmsNotificationService;

/*
İstemci kodu notify() üzerinden ortak iş akışını çalıştırır ve hangi somut
Notification nesnesinin oluşturulduğuyla ilgilenmez.

Factory Method'un temel amacı tam olarak budur: nesne oluşturma kararını
alt sınıflara bırakarak üretim sürecini kullanım mantığından ayırmak.
*/

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