package tr.com.huseyinaydin.sms;

import tr.com.huseyinaydin.notification.Notification;

/*
SmsNotification da aynı sözleşmeyi uygulayarak SMS gönderme davranışını
kapsüller ve yeni bir bildirim türünün mevcut yapıyı bozmasını engeller.

Böylece sistem Open/Closed Principle doğrultusunda yeni bildirim türleri
eklenmesine açık, mevcut kodun değiştirilmesine ise mümkün olduğunca kapalı hâle gelir.
*/

public final class SmsNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("SMS gönderildi: " + message);
    }
}