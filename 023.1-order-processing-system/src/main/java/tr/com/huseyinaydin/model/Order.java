package tr.com.huseyinaydin.model;

public class Order {

    private final long id;
    private final String customerName;
    private final String productName;

    public Order(long id, String customerName, String productName) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }
}