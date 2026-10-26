package tr.com.huseyinaydin;

final class ReturnRequestedState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.RETURN_REQUESTED;
    }
}