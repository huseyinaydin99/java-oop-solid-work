package tr.com.huseyinaydin.infrastructure.persistence;

import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.domain.entity.Account;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<UUID, Account> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void save(Account account) {
        store.put(account.getId(), account);
    }
}
