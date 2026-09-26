package tr.com.huseyinaydin.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

public class MoneyWithdrawnEvent implements DomainEvent {
    private final UUID accountId;
    private final BigDecimal amount;

    public MoneyWithdrawnEvent(UUID accountId, BigDecimal amount) {
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
