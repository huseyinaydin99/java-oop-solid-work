package tr.com.huseyinaydin;

final class DeliveredState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.DELIVERED;
    }

    @Override
    public void requestReturn(Order order) {
        order.transitionTo(new ReturnRequestedState());
    }
}