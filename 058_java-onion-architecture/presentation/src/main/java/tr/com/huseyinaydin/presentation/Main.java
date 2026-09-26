package tr.com.huseyinaydin.presentation;

import com.sun.net.httpserver.HttpServer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tr.com.huseyinaydin.application.port.in.TransferMoneyUseCase;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.application.port.out.TransactionManager;
import tr.com.huseyinaydin.application.service.TransferMoneyService;
import tr.com.huseyinaydin.domain.entity.Account;
import tr.com.huseyinaydin.domain.event.MoneyDepositedEvent;
import tr.com.huseyinaydin.domain.event.MoneyWithdrawnEvent;
import tr.com.huseyinaydin.infrastructure.decorator.*;
import tr.com.huseyinaydin.infrastructure.event.InMemoryDomainEventPublisher;
import tr.com.huseyinaydin.infrastructure.persistence.AccountJpaEntity;
import tr.com.huseyinaydin.infrastructure.persistence.HibernateAccountRepository;
import tr.com.huseyinaydin.infrastructure.persistence.HibernateTransactionManager;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.addAnnotatedClass(AccountJpaEntity.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        AccountRepository accountRepository = new HibernateAccountRepository(sessionFactory);
        TransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        InMemoryDomainEventPublisher eventPublisher = new InMemoryDomainEventPublisher();
        eventPublisher.subscribe(event -> {
            if (event instanceof MoneyWithdrawnEvent) {
                MoneyWithdrawnEvent e = (MoneyWithdrawnEvent) event;
                System.out.println("EVENT LISTENER [SMS]: Çekilen para (" + e.getAmount() + ") hesap " + e.getAccountId());
            } else if (event instanceof MoneyDepositedEvent) {
                MoneyDepositedEvent e = (MoneyDepositedEvent) event;
                System.out.println("EVENT LISTENER [EMAIL]: Yatırılan para (" + e.getAmount() + ") hesap " + e.getAccountId());
            }
        });

        TransferMoneyUseCase transferMoneyService = new TransferMoneyService(accountRepository, eventPublisher);
        
        TransferMoneyUseCase transactionalDecorator = new TransactionalTransferMoneyUseCaseDecorator(transferMoneyService, transactionManager);
        TransferMoneyUseCase cachingDecorator = new CachingTransferMoneyUseCaseDecorator(transactionalDecorator);
        TransferMoneyUseCase securityDecorator = new SecurityTransferMoneyUseCaseDecorator(cachingDecorator);
        TransferMoneyUseCase loggingDecorator = new LoggingTransferMoneyUseCaseDecorator(securityDecorator);
        TransferMoneyUseCase exceptionHandlingDecorator = new ExceptionHandlingTransferMoneyUseCaseDecorator(loggingDecorator);

        UUID account1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID account2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

        try (org.hibernate.Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            accountRepository.save(new Account(account1Id, new BigDecimal("1000")));
            accountRepository.save(new Account(account2Id, new BigDecimal("500")));
            session.getTransaction().commit();
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/transfer", new TransferHandler(exceptionHandlingDecorator));
        server.setExecutor(null);
        server.start();
    }
}
