package tr.com.huseyinaydin.notification;

public abstract class NotificationService {

    public void notify(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }

    protected abstract Notification createNotification();
}