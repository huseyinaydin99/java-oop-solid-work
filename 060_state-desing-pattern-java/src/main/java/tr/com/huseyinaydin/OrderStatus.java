package tr.com.huseyinaydin;

public enum OrderStatus {

    PENDING("Ödeme Bekleniyor"),
    PAID("Ödendi"),
    SHIPPED("Kargoda"),
    DELIVERED("Teslim Edildi"),
    CANCELLED("İptal Edildi"),
    RETURN_REQUESTED("İade Talep Edildi");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}