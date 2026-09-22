package tr.com.huseyinaydin.infrastructure.decorator;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;
import tr.com.huseyinaydin.application.port.out.TransactionManager;

public class TransactionalTransferMoneyUseCaseDecorator implements TransferMoneyUseCase {
    private final TransferMoneyUseCase delegate;
    private final TransactionManager transactionManager;

    public TransactionalTransferMoneyUseCaseDecorator(TransferMoneyUseCase delegate, TransactionManager transactionManager) {
        this.delegate = delegate;
        this.transactionManager = transactionManager;
    }

    @Override
    public void transfer(TransferRequest request) {
        transactionManager.begin();
        try {
            delegate.transfer(request);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
