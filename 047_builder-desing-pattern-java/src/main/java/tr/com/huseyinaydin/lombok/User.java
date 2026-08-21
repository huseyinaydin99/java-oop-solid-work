package tr.com.huseyinaydin.lombok;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private final String username;
    private final String email;
    private final String phone;
    private final String address;
    @Builder.Default // Builder üzerinden o alana değer verilmediğinde tanımladığın varsayılan değerin kullanılmasını sağlar.
    private final boolean active = true;
}