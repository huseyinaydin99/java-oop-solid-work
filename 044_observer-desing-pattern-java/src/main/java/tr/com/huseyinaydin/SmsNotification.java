package tr.com.huseyinaydin;

public class SmsNotification implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println(
                "SMS gönderildi: Sipariş durumu → " + order.getStatus()
        );
    }
}