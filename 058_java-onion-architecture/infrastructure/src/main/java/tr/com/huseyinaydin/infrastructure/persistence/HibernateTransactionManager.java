package tr.com.huseyinaydin.infrastructure.persistence;

import org.hibernate.SessionFactory;
import tr.com.huseyinaydin.application.port.out.TransactionManager;

public class HibernateTransactionManager implements TransactionManager {
    private final SessionFactory sessionFactory;

    public HibernateTransactionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void begin() {
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @Override
    public void commit() {
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void rollback() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
