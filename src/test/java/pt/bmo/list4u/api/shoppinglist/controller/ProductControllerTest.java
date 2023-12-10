package pt.bmo.list4u.api.shoppinglist.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@WebFluxTest(controllers = ProductController.class)
@AutoConfigureWebTestClient
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    private static final String BASE_URL = "/api/products";

    @Test
    void should_find_product_by_id() throws Exception {
        final Product product = Product.builder()
                .id(1)
                .name(FAKE_STR)
                .build();

        Mockito.when(productService.getById(Mockito.anyLong())).thenReturn(Mono.just(product));

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
    void when_id_inexistent_then_return_not_found() throws Exception {
        Mockito.when(productService.getById(Mockito.anyLong())).thenReturn(Mono.empty());

        webTestClient.get()
                .uri(BASE_URL + "/{id}", 1)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void should_get_all_products() throws Exception {
        var productList = List.of(
            Product.builder().id(1).name(FAKE_STR).build(),
            Product.builder().id(2).name(FAKE_STR).build()
        );

        Mockito.when(productService.getAll()).thenReturn(Flux.fromIterable(productList));

        webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Product.class)
                .hasSize(2);
    }

    @Test
    void should_create_a_new_product() throws Exception {
        final Product product = new Product(1,"Beer");

        Mockito.when(productService.create(Mockito.any())).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(product)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Product.class)
                .consumeWith(productEntityExchangeResult -> {
                    var responseBody = productEntityExchangeResult.getResponseBody();
                    assertEquals(1, responseBody.id());
                    assertEquals("Beer", responseBody.name());
                });
    }

    @Test
    void should_update_a_product() throws Exception {
        final Product product = new Product(1,"Beer");

        Mockito.when(productService.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.just(product));

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1)
                .bodyValue(product)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Product.class)
                .consumeWith(productEntityExchangeResult -> {
                    var responseBody = productEntityExchangeResult.getResponseBody();
                    assertEquals(1, responseBody.id());
                    assertEquals("Beer", responseBody.name());
                });
    }

    @Test
    void when_update_a_product_with_inexistent_id_then_return_not_found() throws Exception {
        final Product product = new Product(1, "Product Test");

        Mockito.when(productService.update(Mockito.anyLong(), Mockito.any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri(BASE_URL + "/{id}", 1)
                .bodyValue(product)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

}