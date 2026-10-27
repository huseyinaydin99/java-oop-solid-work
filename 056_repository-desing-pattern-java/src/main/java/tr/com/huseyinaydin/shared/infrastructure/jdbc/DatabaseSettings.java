package tr.com.huseyinaydin.shared.infrastructure.jdbc;

/** Örnek DB_URL: jdbc:oracle:thin:@//localhost:1521/FREEPDB1 */
public record DatabaseSettings(String url, String username, String password, int maxPoolSize) {

    public static DatabaseSettings fromEnvironment() {
        return new DatabaseSettings(
                required("DB_URL"),
                required("DB_USER"),
                required("DB_PASSWORD"),
                Integer.parseInt(System.getenv().getOrDefault("DB_POOL_SIZE", "10")));
    }

    private static String required(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(key + " ortam değişkeni tanımlı değil");
        }
        return value;
    }

    @Override
    public String toString() {
        return "DatabaseSettings[url=%s, username=%s, password=***, maxPoolSize=%d]"
                .formatted(url, username, maxPoolSize);
    }
}
