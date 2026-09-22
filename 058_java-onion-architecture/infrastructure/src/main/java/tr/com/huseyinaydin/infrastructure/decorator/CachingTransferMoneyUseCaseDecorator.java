package tr.com.huseyinaydin.infrastructure.decorator;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CachingTransferMoneyUseCaseDecorator implements TransferMoneyUseCase {
    private final TransferMoneyUseCase delegate;
    private final Set<String> cache = ConcurrentHashMap.newKeySet();

    public CachingTransferMoneyUseCaseDecorator(TransferMoneyUseCase delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transfer(TransferRequest request) {
        String cacheKey = request.getFromAccountId().toString() + "-" + request.getToAccountId().toString() + "-" + request.getAmount().toString();
        if (cache.contains(cacheKey)) {
            throw new IllegalStateException("Önbellekte tekrarlanan transfer isteği algılandı.");
        }
        delegate.transfer(request);
        cache.add(cacheKey);
    }
}
