package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.bmo.list4u.api.shoppinglist.entity.ProductEntity;
import pt.bmo.list4u.api.shoppinglist.mapper.ProductMapper;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private Pageable pageable;
    @Mock
    private Page page;
    @Captor
    private ArgumentCaptor<ProductEntity> productArgumentCaptor;

    private Product create() {
        return new Product(FakeValues.FAKE_LONG, FakeValues.FAKE_STR);
    }

    @Test
    void when_get_by_id_then_return_product() {
        final var product = create();
        when(repository.findById(anyLong()))
                .thenReturn(Mono.just(ProductMapper.INSTANCE.domainToEntity(product)));

        var productMono = service.getById(FakeValues.FAKE_LONG);

        StepVerifier.create(productMono)
                .expectNextCount(1)
                .assertNext(productFound -> {
                    assertEquals(productFound.id(), product.id());
                    assertEquals(productFound.name(), product.name());
                });
    }

    @Test
    void when_get_by_inexistent_id_then_return_empty() {
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        var productMono = service.getById(FakeValues.FAKE_LONG);

        StepVerifier.create(productMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_create_product_then_verify_repository_save() {
        final var product = create();
        when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(ProductMapper.INSTANCE.domainToEntity(product)));

        var productMono = service.create(product);

        verify(repository).save(productArgumentCaptor.capture());
        ProductEntity productArgumentCaptorValue = productArgumentCaptor.getValue();

        StepVerifier.create(productMono)
                .assertNext(productCreated -> {
                    assertEquals(productArgumentCaptorValue.getId(), productCreated.id());
                    assertEquals(productArgumentCaptorValue.getName(), productCreated.name());
                });
    }

    @Test
    void when_update_existent_product_then_verify_repository_and_return_it() {
        final var productMock = create();
        final var product = Product.builder()
                        .id(FakeValues.FAKE_LONG)
                        .name("TEST")
                        .build();

        when(repository.findById(anyLong()))
                .thenReturn(Mono.just(ProductMapper.INSTANCE.domainToEntity(productMock)));

        var productMono = service.update(FakeValues.FAKE_LONG, product);

        StepVerifier.create(productMono)
                .expectNextCount(1)
                .assertNext(productUpdated -> {
                    assertEquals(product, productUpdated);
                });
    }

    @Test
    void when_update_inexistent_product_then_return_empty() {
        final var product = create();
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        var productMono = service.update(FakeValues.FAKE_LONG, product);

        StepVerifier.create(productMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_get_all_then_return_flux_of_products() {
        var productsList = List.of(
            ProductMapper.INSTANCE.domainToEntity(Product.builder()
                .id(FakeValues.FAKE_LONG)
                .name("TEST")
                .build())
        );

        when(repository.findAll()).thenReturn(Flux.fromIterable(productsList));

        var productFlux = service.getAll();

        StepVerifier.create(productFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
}