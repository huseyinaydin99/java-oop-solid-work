package tr.com.huseyinaydin.application.port.out;

import tr.com.huseyinaydin.domain.entity.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id);
    void save(Account account);
}
