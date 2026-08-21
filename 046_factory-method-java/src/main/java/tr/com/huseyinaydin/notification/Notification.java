package tr.com.huseyinaydin.notification;

/*
Notification, tüm bildirim türlerinin uyması gereken ortak sözleşmeyi tanımlar;
böylece istemci kodu Email veya SMS gibi ayrıntılara bağımlı kalmaz.
Bu yapı Dependency Inversion açısından da daha sağlıklıdır çünkü üst seviye
kod somut sınıflara değil, soyutlamaya dayanır.
*/

public interface Notification {
    void send(String message);
}