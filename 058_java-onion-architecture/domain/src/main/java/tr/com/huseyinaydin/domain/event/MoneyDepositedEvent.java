package tr.com.huseyinaydin.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

public class MoneyDepositedEvent implements DomainEvent {
    private final UUID accountId;
    private final BigDecimal amount;

    public MoneyDepositedEvent(UUID accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
