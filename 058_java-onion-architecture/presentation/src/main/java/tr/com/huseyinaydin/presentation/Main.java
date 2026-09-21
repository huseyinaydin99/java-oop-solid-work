package tr.com.huseyinaydin.presentation;

import com.sun.net.httpserver.HttpServer;
import tr.com.huseyinaydin.application.port.out.AccountRepository;
import tr.com.huseyinaydin.application.service.TransferMoneyService;
import tr.com.huseyinaydin.domain.entity.Account;
import tr.com.huseyinaydin.infrastructure.persistence.JdbcAccountRepository;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1");
        
        AccountRepository accountRepository = new JdbcAccountRepository(connection);
        TransferMoneyService transferMoneyService = new TransferMoneyService(accountRepository);

        UUID account1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID account2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");
        
        accountRepository.save(new Account(account1Id, new BigDecimal("1000")));
        accountRepository.save(new Account(account2Id, new BigDecimal("500")));

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/transfer", new TransferHandler(transferMoneyService));
        server.setExecutor(null);
        server.start();
    }
}
