package tr.com.huseyinaydin.infrastructure.decorator;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;

import java.util.logging.Logger;

public class LoggingTransferMoneyUseCaseDecorator implements TransferMoneyUseCase {
    private static final Logger logger = Logger.getLogger(LoggingTransferMoneyUseCaseDecorator.class.getName());
    private final TransferMoneyUseCase delegate;

    public LoggingTransferMoneyUseCaseDecorator(TransferMoneyUseCase delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transfer(TransferRequest request) {
        logger.info("Para transferi başladı: Gönderici; " + request.getFromAccountId() + " alıcı; " + request.getToAccountId() + " Miktar: " + request.getAmount());
        try {
            delegate.transfer(request);
            logger.info("Para transferi başarıyla tamamlandı.");
        } catch (Exception e) {
            logger.severe("Transfer operasyonu başarısız: " + e.getMessage());
            throw e;
        }
    }
}
