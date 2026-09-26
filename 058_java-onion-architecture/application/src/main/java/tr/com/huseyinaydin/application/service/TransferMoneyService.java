package tr.com.huseyinaydin.application.service;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.application.port.out.DomainEventPublisher;
import tr.com.huseyinaydin.domain.entity.Account;

public class TransferMoneyService implements TransferMoneyUseCase {
    private final AccountRepository accountRepository;
    private final DomainEventPublisher domainEventPublisher;

    public TransferMoneyService(AccountRepository accountRepository, DomainEventPublisher domainEventPublisher) {
        this.accountRepository = accountRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void transfer(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(RuntimeException::new);
        
        Account toAccount = accountRepository.findById(request.getToAccountId())
                .orElseThrow(RuntimeException::new);

        fromAccount.withdraw(request.getAmount());
        toAccount.deposit(request.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        fromAccount.clearDomainEvents().forEach(domainEventPublisher::publish);
        toAccount.clearDomainEvents().forEach(domainEventPublisher::publish);
    }
}
