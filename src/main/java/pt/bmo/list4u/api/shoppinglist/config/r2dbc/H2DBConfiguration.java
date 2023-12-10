package pt.bmo.list4u.api.shoppinglist.config.r2dbc;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@Profile("test")
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class H2DBConfiguration extends AbstractR2dbcConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2DBConfiguration.class);

    @Bean("h2ConnectionFactoryBean")
    @Override
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .inMemory("list4u-test-db")
                .build()
        );
    }

    @PostConstruct
    void initialize() throws IOException {
        R2dbcEntityTemplate r2dbcEntityTemplate = new R2dbcEntityTemplate(connectionFactory());

        ClassPathResource classPathResource = new ClassPathResource("sql/schema.sql");
        Mono<Void> populateH2DB = new ResourceDatabasePopulator(classPathResource)
                .populate(connectionFactory());

        populateH2DB.subscribe(data -> LOGGER.info("data: {}", data), error -> LOGGER.error("error: {}", error));

        r2dbcEntityTemplate.getDatabaseClient()
                .sql(classPathResource.getContentAsString(StandardCharsets.UTF_8))
                .then()
                .subscribe(data -> LOGGER.info("data: {}", data), error -> LOGGER.error("error: {}", error));
    }
}
