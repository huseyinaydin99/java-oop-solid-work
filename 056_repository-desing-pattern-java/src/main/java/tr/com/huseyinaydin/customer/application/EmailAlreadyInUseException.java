package tr.com.huseyinaydin.customer.application;

import tr.com.huseyinaydin.customer.domain.Email;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException(Email email) {
        this(email, null);
    }

    public EmailAlreadyInUseException(Email email, Throwable cause) {
        super("Bu e-posta adresi zaten kullanımda: " + email.value(), cause);
    }
}
