package tr.com.huseyinaydin;

import com.zaxxer.hikari.HikariDataSource;
import tr.com.huseyinaydin.customer.application.CustomerService;
import tr.com.huseyinaydin.customer.domain.Customer;
import tr.com.huseyinaydin.customer.domain.CustomerRepository;
import tr.com.huseyinaydin.customer.infrastructure.JdbcCustomerRepository;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.DatabaseSettings;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.JdbcExecutor;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.JdbcTransactionRunner;
import tr.com.huseyinaydin.shared.infrastructure.jdbc.OracleDataSourceFactory;
import tr.com.huseyinaydin.shared.persistence.PageRequest;

public final class Application {

    public static void main(String[] args) {
        try (HikariDataSource dataSource = OracleDataSourceFactory.create(DatabaseSettings.fromEnvironment())) {
            JdbcTransactionRunner transactions = new JdbcTransactionRunner(dataSource);
            CustomerRepository customers = new JdbcCustomerRepository(new JdbcExecutor(transactions));
            CustomerService service = new CustomerService(customers, transactions);

            Customer huseyin = service.register("Hüseyin Aydın", "huseyinaydin99@gmail.com");
            System.out.println("Kaydedildi: " + huseyin);

            service.deactivate(huseyin.id());
            System.out.println("Aktif müşteriler: " + service.activeCustomers(PageRequest.of(0, 20)));
        }
    }
}
