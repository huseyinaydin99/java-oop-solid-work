package tr.com.huseyinaydin.domain.entity;

import tr.com.huseyinaydin.domain.exception.InsufficientBalanceException;
import tr.com.huseyinaydin.domain.event.MoneyDepositedEvent;
import tr.com.huseyinaydin.domain.event.MoneyWithdrawnEvent;

import java.math.BigDecimal;
import java.util.UUID;

public class Account extends AggregateRoot {
    private final UUID id;
    private BigDecimal balance;

    public Account(UUID id, BigDecimal initialBalance) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.balance = initialBalance;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = this.balance.subtract(amount);
        registerEvent(new MoneyWithdrawnEvent(this.id, amount));
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        this.balance = this.balance.add(amount);
        registerEvent(new MoneyDepositedEvent(this.id, amount));
    }
}
