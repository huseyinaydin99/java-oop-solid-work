package tr.com.huseyinaydin;

final class PendingState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.PENDING;
    }

    @Override
    public void pay(Order order) {
        order.transitionTo(new PaidState());
    }

    @Override
    public void cancel(Order order) {
        order.transitionTo(new CancelledState());
    }
}