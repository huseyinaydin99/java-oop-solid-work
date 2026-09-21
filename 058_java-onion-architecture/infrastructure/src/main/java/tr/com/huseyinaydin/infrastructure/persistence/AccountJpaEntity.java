package tr.com.huseyinaydin.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tr.com.huseyinaydin.domain.entity.Account;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountJpaEntity {
    @Id
    private String id;
    private BigDecimal balance;

    public AccountJpaEntity() {
    }

    public AccountJpaEntity(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account toDomainEntity() {
        return new Account(UUID.fromString(id), balance);
    }

    public static AccountJpaEntity fromDomainEntity(Account account) {
        return new AccountJpaEntity(account.getId().toString(), account.getBalance());
    }
}
