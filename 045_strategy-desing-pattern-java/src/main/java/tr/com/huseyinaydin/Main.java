package tr.com.huseyinaydin;

public class Main {

    public static void main(String[] args) {
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentService service = new PaymentService(creditCard);

        service.process(1500);

        service = new PaymentService(new BankTransferPayment());
        service.process(2500);
    }
}
