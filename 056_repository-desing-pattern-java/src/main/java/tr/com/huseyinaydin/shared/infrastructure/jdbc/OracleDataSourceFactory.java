package tr.com.huseyinaydin.shared.infrastructure.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class OracleDataSourceFactory {

    private OracleDataSourceFactory() {
    }

    public static HikariDataSource create(DatabaseSettings settings) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(settings.url());
        config.setUsername(settings.username());
        config.setPassword(settings.password());
        config.setMaximumPoolSize(settings.maxPoolSize());
        config.setPoolName("customer-pool");
        return new HikariDataSource(config);
    }
}
