package tr.com.huseyinaydin.email;

/*
EmailNotification, Notification sözleşmesini gerçekleştirerek e-posta
gönderme davranışını kendi sorumluluğunda tutar. SRP

İstemci bu sınıfın iç çalışma detaylarını bilmez;
yalnızca Notification üzerinden davranışı kullanır.
*/

import tr.com.huseyinaydin.notification.Notification;

public final class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("E-posta gönderildi: " + message);
    }
}