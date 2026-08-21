package tr.com.huseyinaydin;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Kredi kartıyla " + amount + " TL ödendi.");
    }
}