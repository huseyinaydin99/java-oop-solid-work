package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {

        Order order = new Order("HAZIRLANIYOR");

        OrderObserver email = new EmailNotification();
        OrderObserver sms = new SmsNotification();

        order.addObserver(email);
        order.addObserver(sms);

        order.setStatus("KARGODA");
    }
}
