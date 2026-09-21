package tr.com.huseyinaydin.infrastructure.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        try (Session session = sessionFactory.openSession()) {
            AccountJpaEntity entity = session.get(AccountJpaEntity.class, id.toString());
            if (entity != null) {
                return Optional.of(entity.toDomainEntity());
            }
            return Optional.empty();
        }
    }

    @Override
    public void save(Account account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                AccountJpaEntity entity = AccountJpaEntity.fromDomainEntity(account);
                session.merge(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
