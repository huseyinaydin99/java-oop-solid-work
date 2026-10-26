package tr.com.huseyinaydin;

final class PaidState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.PAID;
    }

    @Override
    public void ship(Order order) {
        order.transitionTo(new ShippedState());
    }

    @Override
    public void cancel(Order order) {
        order.recordEvent("Ödeme iadesi başlatıldı");
        order.transitionTo(new CancelledState());
    }
}