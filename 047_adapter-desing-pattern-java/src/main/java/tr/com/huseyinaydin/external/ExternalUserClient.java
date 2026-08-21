package tr.com.huseyinaydin.external;

public class ExternalUserClient {
    public ExternalUserResponse fetchUser(Long userId) {
        // Gerçekte HTTP isteği yapılır.
        return new ExternalUserResponse(
                userId,
                "Hüseyin",
                "huseyinaydin99@gmail.com"
        );
    }
}