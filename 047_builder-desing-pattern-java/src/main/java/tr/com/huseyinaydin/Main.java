package tr.com.huseyinaydin;

public class Main {

    public static void main(String[] args) {

        // Builder tasarım şablonulu olan; güzelll
        User user = User.builder("huseyin", "huseyinaydin99@gmail.com")
                .phone("5551234567")
                .address("Niğde")
                .active(true)
                .build();

        // ❌ Builder tasarım şablonu olmadan örnek; hiç güzel değil!
        /*
        new User(
                "huseyin",
                "huseyin@example.com",
                "5551234567",
                "Niğde",
                true
        );
        */
    }
}
