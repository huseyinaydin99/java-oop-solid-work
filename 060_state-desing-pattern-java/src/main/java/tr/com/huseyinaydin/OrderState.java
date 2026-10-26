package tr.com.huseyinaydin;

/**
 * Bir siparişin yaşam döngüsündeki tek bir durumu temsil eder.
 *
 * <p>Sözleşme: Durum, kendisinde geçerli olmayan bir işlem istendiğinde
 * {@link IllegalStateTransitionException} fırlatır. Geçerli bir işlemde ise
 * siparişi bir sonraki duruma taşımaktan kendisi sorumludur.
 */
interface OrderState {

    OrderStatus status();

    default void pay(Order order) {
        throw rejected("ödeme");
    }

    default void ship(Order order) {
        throw rejected("kargoya verme");
    }

    default void deliver(Order order) {
        throw rejected("teslim");
    }

    default void cancel(Order order) {
        throw rejected("iptal");
    }

    default void requestReturn(Order order) {
        throw rejected("iade talebi");
    }

    private IllegalStateTransitionException rejected(String action) {
        return new IllegalStateTransitionException(status(), action);
    }
}