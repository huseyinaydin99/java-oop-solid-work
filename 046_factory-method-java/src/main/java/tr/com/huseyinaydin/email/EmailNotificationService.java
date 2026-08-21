package tr.com.huseyinaydin.email;

import tr.com.huseyinaydin.notification.Notification;
import tr.com.huseyinaydin.notification.NotificationService;

public final class EmailNotificationService extends NotificationService {

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}