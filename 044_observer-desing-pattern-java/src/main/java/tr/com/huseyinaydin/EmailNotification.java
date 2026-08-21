package tr.com.huseyinaydin;

public class EmailNotification implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println(
                "E-posta gönderildi: Sipariş durumu → " + order.getStatus()
        );
    }
}