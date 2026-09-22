package tr.com.huseyinaydin.infrastructure.decorator;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;

public class ExceptionHandlingTransferMoneyUseCaseDecorator implements TransferMoneyUseCase {
    private final TransferMoneyUseCase delegate;

    public ExceptionHandlingTransferMoneyUseCaseDecorator(TransferMoneyUseCase delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transfer(TransferRequest request) {
        try {
            delegate.transfer(request);
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            throw new RuntimeException("Doğrulama Hatası: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Aktarım sırasında sistem hatası oluştu.", e);
        }
    }
}
