package pt.bmo.list4u.api.shoppinglist.config.r2dbc;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
@EnableTransactionManagement
public class PostgresDBConfiguration extends AbstractR2dbcConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresDBConfiguration.class);

    private final PostgresProperties postgresProperties;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(postgresProperties.getHost())
                .port(postgresProperties.getPort())
                .database(postgresProperties.getDatabase())
                .username(postgresProperties.getUsername())
                .password(postgresProperties.getPassword())
                .sslMode(SSLMode.REQUIRE)
                .build());
    }

}
