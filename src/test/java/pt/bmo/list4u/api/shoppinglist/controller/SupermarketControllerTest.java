package pt.bmo.list4u.api.shoppinglist.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.service.SupermarketService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@WebFluxTest(controllers = SupermarketController.class)
@AutoConfigureWebTestClient
class SupermarketControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private SupermarketService service;
    private static final String BASE_URL = "/api/supermarkets";

    @Test
    void should_find_supermarket_by_id() throws Exception {
        final Supermarket supermarket = Supermarket.builder()
                .id(1)
                .name(FAKE_STR)
                .country(Country.BRAZIL)
                .build();

        Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Mono.just(supermarket));

        webTestClient.get()
                .uri(BASE_URL + "/{id}", 1234)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo(FAKE_STR);
    }

    @Test
    void when_inexistent_id_then_return_not_found() throws Exception {
        Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Mono.empty());

        webTestClient.get()
                .uri(BASE_URL + "/{id}", 1234)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    void should_get_all_supermarkets() throws Exception {
        var supermarketList = List.of(Supermarket.builder()
                .name(FAKE_STR)
                .country(Country.BRAZIL)
                .build());

        Mockito.when(service.getAll()).thenReturn(Flux.fromIterable(supermarketList));

        webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Supermarket.class)
                .hasSize(1);
    }

    @Test
    void should_create_a_supermarket() throws Exception {
        final var supermarketName = "Carrefour";
        final Supermarket supermarket = Supermarket.builder()
                .name(supermarketName)
                .country(Country.BRAZIL)
        .build();

        final var response = Supermarket.builder()
                .id(1l)
                .name(supermarketName)
                .country(Country.BRAZIL)
                .build();

        Mockito.when(service.create(Mockito.any())).thenReturn(Mono.just(response));

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(supermarket)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Supermarket.class)
                .consumeWith(supermarketEntityExchangeResult -> {
                    var responseBody = supermarketEntityExchangeResult.getResponseBody();
                    assertEquals(1, responseBody.id());
                    assertEquals(supermarketName, responseBody.name());
                    assertEquals(Country.BRAZIL, responseBody.country());
                });
    }

    @Test
    void should_update_a_supermarket() throws Exception {
        final var supermarketName = "Extra";
        final Supermarket supermarket = Supermarket.builder()
                .name(supermarketName)
                .country(Country.BRAZIL)
                .build();

        final var response = Supermarket.builder()
                .id(1l)
                .name(supermarketName)
                .country(Country.BRAZIL)
                .build();

        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.just(response));

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1l)
                .bodyValue(supermarket)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Supermarket.class)
                .consumeWith(supermarketEntityExchangeResult -> {
                    var responseBody = supermarketEntityExchangeResult.getResponseBody();
                    assertEquals(1, responseBody.id());
                    assertEquals(supermarketName, responseBody.name());
                    assertEquals(Country.BRAZIL, responseBody.country());
                });
    }

    @Test
    void when_updated_with_inexistent_id_then_return_not_found() throws Exception {
        final var supermarketName = "Extra";
        final Supermarket supermarket = Supermarket.builder()
                .name(supermarketName)
                .country(Country.BRAZIL)
                .build();

        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1234)
                .bodyValue(supermarket)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    void should_delete_a_supermarket() throws Exception {
        Mockito.when(service.delete(Mockito.anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(BASE_URL + "/{id}", 1)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}