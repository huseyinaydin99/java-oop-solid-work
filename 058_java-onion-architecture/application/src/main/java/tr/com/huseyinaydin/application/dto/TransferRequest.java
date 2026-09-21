package tr.com.huseyinaydin.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferRequest {
    private final UUID fromAccountId;
    private final UUID toAccountId;
    private final BigDecimal amount;

    public TransferRequest(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public UUID getFromAccountId() {
        return fromAccountId;
    }

    public UUID getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
