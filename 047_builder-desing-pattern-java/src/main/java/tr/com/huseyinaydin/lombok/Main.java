package tr.com.huseyinaydin.lombok;

public class Main {
    public static void main(String[] args) {
        User user = User.builder()
                .username("huseyin")
                .email("huseyin@example.com")
                .phone("5551234567")
                .address("Niğde")
                .active(true)
                .build();
    }
}
