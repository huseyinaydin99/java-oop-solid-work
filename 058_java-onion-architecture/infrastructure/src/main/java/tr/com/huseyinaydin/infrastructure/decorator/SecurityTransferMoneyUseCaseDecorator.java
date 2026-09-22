package tr.com.huseyinaydin.infrastructure.decorator;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;

public class SecurityTransferMoneyUseCaseDecorator implements TransferMoneyUseCase {
    private final TransferMoneyUseCase delegate;

    public SecurityTransferMoneyUseCaseDecorator(TransferMoneyUseCase delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transfer(TransferRequest request) {
        if ("LOCKED".equals(System.getProperty("APP_SECURITY_STATE"))) {
            throw new SecurityException("Sistem transferlere kapalıdır.");
        }
        delegate.transfer(request);
    }
}
