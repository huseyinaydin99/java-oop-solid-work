package tr.com.huseyinaydin.presentation;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.application.service.TransferMoneyService;
import tr.com.huseyinaydin.domain.entity.Account;
import tr.com.huseyinaydin.infrastructure.persistence.InMemoryAccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyService(accountRepository);

        UUID account1Id = UUID.randomUUID();
        UUID account2Id = UUID.randomUUID();

        accountRepository.save(new Account(account1Id, new BigDecimal("1000")));
        accountRepository.save(new Account(account2Id, new BigDecimal("500")));

        TransferRequest request = new TransferRequest(account1Id, account2Id, new BigDecimal("250"));
        transferMoneyUseCase.transfer(request);

        Account account1 = accountRepository.findById(account1Id).orElseThrow();
        Account account2 = accountRepository.findById(account2Id).orElseThrow();

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
    }
}
