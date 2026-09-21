package tr.com.huseyinaydin.application.service;

import tr.com.huseyinaydin.application.dto.TransferRequest;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.domain.entity.Account;
import tr.com.huseyinaydin.domain.exception.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferMoneyServiceTest {

    private AccountRepository fakeRepository;
    private TransferMoneyService transferMoneyService;

    @BeforeEach
    void setUp() {
        fakeRepository = new FakeAccountRepository();
        transferMoneyService = new TransferMoneyService(fakeRepository);
    }

    @Test
    void should_transfer_money_between_accounts() {
        UUID fromId = UUID.randomUUID();
        UUID toId = UUID.randomUUID();
        
        fakeRepository.save(new Account(fromId, new BigDecimal("500")));
        fakeRepository.save(new Account(toId, new BigDecimal("100")));

        TransferRequest request = new TransferRequest(fromId, toId, new BigDecimal("200"));
        transferMoneyService.transfer(request);

        Account updatedFrom = fakeRepository.findById(fromId).orElseThrow();
        Account updatedTo = fakeRepository.findById(toId).orElseThrow();

        assertEquals(new BigDecimal("300"), updatedFrom.getBalance());
        assertEquals(new BigDecimal("300"), updatedTo.getBalance());
    }

    @Test
    void should_fail_transfer_if_insufficient_balance() {
        UUID fromId = UUID.randomUUID();
        UUID toId = UUID.randomUUID();
        
        fakeRepository.save(new Account(fromId, new BigDecimal("100")));
        fakeRepository.save(new Account(toId, new BigDecimal("100")));

        TransferRequest request = new TransferRequest(fromId, toId, new BigDecimal("200"));
        
        assertThrows(InsufficientBalanceException.class, () -> {
            transferMoneyService.transfer(request);
        });

        Account unchangedFrom = fakeRepository.findById(fromId).orElseThrow();
        Account unchangedTo = fakeRepository.findById(toId).orElseThrow();

        assertEquals(new BigDecimal("100"), unchangedFrom.getBalance());
        assertEquals(new BigDecimal("100"), unchangedTo.getBalance());
    }

    private static class FakeAccountRepository implements AccountRepository {
        private final Map<UUID, Account> database = new HashMap<>();

        @Override
        public Optional<Account> findById(UUID id) {
            return Optional.ofNullable(database.get(id));
        }

        @Override
        public void save(Account account) {
            database.put(account.getId(), account);
        }
    }
}
