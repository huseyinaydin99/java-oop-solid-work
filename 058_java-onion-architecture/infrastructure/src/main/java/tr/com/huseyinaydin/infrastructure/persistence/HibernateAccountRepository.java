package tr.com.huseyinaydin.infrastructure.persistence;

import org.hibernate.SessionFactory;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public class HibernateAccountRepository implements AccountRepository {
    private final SessionFactory sessionFactory;

    public HibernateAccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        AccountJpaEntity entity = sessionFactory.getCurrentSession().get(AccountJpaEntity.class, id.toString());
        if (entity != null) {
            return Optional.of(entity.toDomainEntity());
        }
        return Optional.empty();
    }

    @Override
    public void save(Account account) {
        AccountJpaEntity entity = AccountJpaEntity.fromDomainEntity(account);
        sessionFactory.getCurrentSession().merge(entity);
    }
}
