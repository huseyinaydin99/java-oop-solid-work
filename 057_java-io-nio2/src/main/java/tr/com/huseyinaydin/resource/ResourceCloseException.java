package tr.com.huseyinaydin.resource;

/**
 * Bir kaynak kapatılırken oluşan hata.
 */
public class ResourceCloseException extends RuntimeException {

    public ResourceCloseException(String resourceName) {
        super("Kaynak kapatılamadı: " + resourceName);
    }
}
