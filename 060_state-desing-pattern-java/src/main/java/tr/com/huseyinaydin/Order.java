package tr.com.huseyinaydin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Order {

    private final String id;
    private final List<String> history = new ArrayList<>();
    private OrderState state;

    public Order(String id) {
        this(id, new PendingState());
        recordEvent("Sipariş oluşturuldu: " + state.status().label());
    }

    private Order(String id, OrderState initialState) {
        this.id = Objects.requireNonNull(id, "Sipariş kimliği boş olamaz");
        this.state = Objects.requireNonNull(initialState, "Başlangıç durumu boş olamaz");
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public String id() {
        return id;
    }

    public OrderStatus status() {
        return state.status();
    }

    public List<String> history() {
        return List.copyOf(history);
    }

    // Order.java: dış dünyaya açılan yeni kapı
    public void requestReturn() {
        state.requestReturn(this);
    }

    // Yalnızca aynı paketteki durum sınıflarının kullanabildiği kapılar.

    void transitionTo(OrderState next) {
        OrderStatus previous = state.status();
        state = Objects.requireNonNull(next, "Yeni durum boş olamaz");
        recordEvent(previous.label() + " → " + next.status().label());
    }

    void recordEvent(String event) {
        history.add(event);
    }
}