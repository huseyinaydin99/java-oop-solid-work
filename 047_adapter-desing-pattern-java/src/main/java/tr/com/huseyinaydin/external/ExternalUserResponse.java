package tr.com.huseyinaydin.external;

public record ExternalUserResponse(
        Long userId,
        String fullName,
        String emailAddress
) {
}