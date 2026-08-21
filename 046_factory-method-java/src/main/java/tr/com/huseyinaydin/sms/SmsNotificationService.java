package tr.com.huseyinaydin.sms;

import tr.com.huseyinaydin.notification.Notification;
import tr.com.huseyinaydin.notification.NotificationService;

/*
SmsNotificationService aynı Factory Method'u kullanarak bu kez SmsNotification
üretir ve ortak iş akışını değiştirmeden farklı bir ürün sağlar.

Yeni bir PushNotificationService eklemek istediğimizde mevcut servis mantığına
dokunmadan yalnızca yeni bir Notification ve onu üreten servis oluşturabiliriz.
*/

public final class SmsNotificationService extends NotificationService {

    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}