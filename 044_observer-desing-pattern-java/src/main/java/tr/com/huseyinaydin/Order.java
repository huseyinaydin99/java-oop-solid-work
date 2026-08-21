package tr.com.huseyinaydin;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderObserver> observers = new ArrayList<>();

    private String status; // durum her değiştiğinde tüm somut gözlemcilere bildirilir!

    public Order(String status) {
        this.status = status;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getStatus() {
        return status;
    }
}