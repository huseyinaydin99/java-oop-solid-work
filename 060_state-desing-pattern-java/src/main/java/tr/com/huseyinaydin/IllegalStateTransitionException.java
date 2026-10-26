package tr.com.huseyinaydin;

public class IllegalStateTransitionException extends IllegalStateException {

    public IllegalStateTransitionException(OrderStatus status, String action) {
        super("'%s' durumundaki siparişte '%s' işlemi yapılamaz."
                .formatted(status.label(), action));
    }
}