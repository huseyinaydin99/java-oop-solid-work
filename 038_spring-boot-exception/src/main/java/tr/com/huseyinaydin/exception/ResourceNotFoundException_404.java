package tr.com.huseyinaydin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  //404 //Bu dipnot, bir REST endpoint'inde istenen kaynak bulunamadığında 404 HTTP durum kodu dönmesini sağlar.
public class ResourceNotFoundException_404 extends Exception {
    public ResourceNotFoundException_404(String message) {
        super(message);
    }
}