package pt.bmo.list4u.api.shoppinglist.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@DataR2dbcTest
@ActiveProfiles("test")
class SupermarketRepositoryTest {

    @Autowired
    private SupermarketRepository supermarketRepository;

    @BeforeEach
    void setUp() {
        var supermarket = SupermarketEntity.builder()
                .name(FAKE_STR)
                .country(Country.BRAZIL)
                .build();

        supermarketRepository.save(supermarket).block();
    }

    @AfterEach
    void tearsDown() {
        supermarketRepository.deleteAll().block();
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

    @Test
    void when_get_all_then_return_list() {
        Flux<SupermarketEntity> supermarketEntityFlux = supermarketRepository.findAll().log();

        StepVerifier.create(supermarketEntityFlux)
                .assertNext(supermarket -> assertEquals(FAKE_STR, supermarket.getName()))
                .verifyComplete();
    }

    @Test
    void when_update_then_do_it() {
        final var newTxt = "newTxt";
        final Flux<SupermarketEntity> supermarketEntityFlux = supermarketRepository
                .findAll()
                .log()
                .flatMap(supermarket -> {
                    supermarket.setName(newTxt);
                    return supermarketRepository.save(supermarket);
                });

        StepVerifier.create(supermarketEntityFlux)
                .assertNext(supermarket -> assertEquals(newTxt, supermarket.getName()))
                .verifyComplete();
    }

    @Test
    void when_delete_then_do_it() {
        supermarketRepository.deleteAll().block();

        final Flux<SupermarketEntity> supermarketEntityFlux = supermarketRepository.findAll();

        StepVerifier.create(supermarketEntityFlux)
                .expectNextCount(0)
                .verifyComplete();
    }

}