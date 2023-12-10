package pt.bmo.list4u.api.shoppinglist.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartReportsService;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_DOUBLE;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_LONG;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@WebFluxTest(controllers = ShoppingCartController.class)
@AutoConfigureWebTestClient
class ShoppingCartControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ShoppingCartService service;

    @MockBean
    private ShoppingCartReportsService reportsService;

    private static final String BASE_URL = "/api/shopping-carts";

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setup() {
        this.shoppingCart = createShoppingCart();
    }

    @Test
    void should_find_shopping_cart_by_id() {
        Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Mono.just(this.shoppingCart));

        webTestClient.get()
                .uri(BASE_URL + "/{id}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo(FAKE_STR);
    }

    @Test
    void should_retrieve_all_shopping_carts() {
        var shoppingCartList = List.of(createShoppingCart());

        Mockito.when(service.getAll(Mockito.anyMap())).thenReturn(Flux.fromIterable(shoppingCartList));

        webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ShoppingCart.class)
                .hasSize(1);
    }

    @Test
    void should_retrieve_finished_shopping_carts_using_query_params() {
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("finished", "true")
                .buildAndExpand()
                .toUri();

        var shoppingCartList = List.of(createShoppingCart());

        Mockito.when(service.getAllByFinished(Mockito.anyBoolean(), Mockito.any())).thenReturn(Flux.fromIterable(shoppingCartList));

        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ShoppingCart.class)
                .hasSize(1);
    }

    @Test
    void should_retrieve_shopping_carts_by_period_using_query_params() {

        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("byPeriod", "2023-07-20,2023-07-29")
                .buildAndExpand()
                .toUri();

        var shoppingCartList = List.of(createShoppingCart());

        Mockito.when(service.getByPeriod(Mockito.any(), Mockito.any())).thenReturn(Flux.fromIterable(shoppingCartList));

        webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ShoppingCart.class);
    }

    @Test
    void should_create_shopping_cart() {
        Mockito.when(service.create(Mockito.any())).thenReturn(Mono.just(createShoppingCart()));

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(createShoppingCart())
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo(FAKE_STR);
    }

    @Test
    void should_update_shopping_cart_name() {
        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.just(createShoppingCart()));

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1)
                .bodyValue(createShoppingCart())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ShoppingCart.class)
                .consumeWith(shoppingCartEntityExchangeResult -> {
                    var responseBody = shoppingCartEntityExchangeResult.getResponseBody();
                    assertEquals(FAKE_STR, responseBody.name());
                    assertFalse(responseBody.finished());
                });
    }

    @Test
    void when_update_with_id_inexistent_then_return_not_found() {
        var shoppingCart = createShoppingCart();

        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1)
                .bodyValue(shoppingCart)
                .exchange()
                .expectStatus()
                .isNotFound();

    }

    @Test
    void should_delete_a_shopping_cart() {
        Mockito.when(service.delete(Mockito.anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri(BASE_URL + "/{id}", 1)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    private ShoppingCart createShoppingCart() {
        Product beer = new Product(FAKE_LONG, FAKE_STR);

        Supermarket supermarket = new Supermarket(FAKE_LONG, FAKE_STR, Country.PORTUGAL);

        ItemCart itemCart = new ItemCart(FAKE_LONG, beer, FAKE_LONG, FAKE_DOUBLE, false);
        List<ItemCart> items = Arrays.asList(itemCart);

        ShoppingCart shoppingCart = new ShoppingCart(FAKE_LONG, FAKE_STR, items, false, supermarket, LocalDateTime.now());

        return shoppingCart;
    }
}