package tr.com.huseyinaydin;

public class BankTransferPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Havale ile " + amount + " TL ödendi.");
    }
}