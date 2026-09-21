package tr.com.huseyinaydin.domain.entity;

import tr.com.huseyinaydin.domain.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void should_withdraw_money_successfully() {
        Account account = new Account(UUID.randomUUID(), new BigDecimal("100"));
        account.withdraw(new BigDecimal("40"));
        assertEquals(new BigDecimal("60"), account.getBalance());
    }

    @Test
    void should_throw_exception_when_withdrawing_more_than_balance() {
        Account account = new Account(UUID.randomUUID(), new BigDecimal("100"));
        assertThrows(InsufficientBalanceException.class, () -> {
            account.withdraw(new BigDecimal("150"));
        });
    }

    @Test
    void should_deposit_money_successfully() {
        Account account = new Account(UUID.randomUUID(), new BigDecimal("100"));
        account.deposit(new BigDecimal("50"));
        assertEquals(new BigDecimal("150"), account.getBalance());
    }

    @Test
    void should_throw_exception_when_withdrawing_negative_amount() {
        Account account = new Account(UUID.randomUUID(), new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(new BigDecimal("-10"));
        });
    }
}
