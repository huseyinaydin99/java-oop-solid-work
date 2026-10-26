package tr.com.huseyinaydin;

final class ShippedState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.SHIPPED;
    }

    @Override
    public void deliver(Order order) {
        order.transitionTo(new DeliveredState());
    }
}