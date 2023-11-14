package pt.bmo.list4u.api.shoppinglist.repository;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@DataR2dbcTest
@ActiveProfiles("test")
class SupermarketRepositoryTest {

    @Autowired
    private SupermarketRepository supermarketRepository;

    @Autowired
    private static H2ConnectionFactory connectionFactory;

    @BeforeAll
    static void init () throws IOException {
        R2dbcEntityTemplate r2dbcEntityTemplate = new   R2dbcEntityTemplate(connectionFactory);
        ClassPathResource classPathResource = new ClassPathResource("sql/schema.sql");
        r2dbcEntityTemplate.getDatabaseClient()
                .sql(classPathResource.getContentAsString(StandardCharsets.UTF_8))
                .then()
                .subscribe(data -> System.out.println("data: " + data), error -> System.out.println("error: " + error));
    }

    @Test
    void when_save_new_supermarket_then_do_it() {
        var supermarket = SupermarketEntity.builder()
                .name(FAKE_STR)
                .country(Country.BRAZIL)
        .build();

        Mono<SupermarketEntity> supermarketMono = supermarketRepository.save(supermarket).log();

        StepVerifier.create(supermarketMono)
                .assertNext(supermarketEntity -> {
                    assertTrue(supermarketEntity.getId() > 0);
                    assertEquals(FAKE_STR, supermarketEntity.getName());
                })
                .verifyComplete();
    }

}