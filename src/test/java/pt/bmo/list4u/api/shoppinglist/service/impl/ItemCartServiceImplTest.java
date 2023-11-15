package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import pt.bmo.list4u.api.shoppinglist.entity.ItemCartEntity;
import pt.bmo.list4u.api.shoppinglist.mapper.ItemCartMapper;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ItemCartRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ItemCartServiceImplTest {

    @Mock
    private ItemCartRepository repository;

    @InjectMocks
    private ItemCartServiceImpl service;

    @Mock
    private Pageable pageable;

    @Captor
    private ArgumentCaptor<ItemCartEntity> itemCartArgumentCaptor;

    private ItemCart create() {
        Product product = new Product(FakeValues.FAKE_LONG, FakeValues.FAKE_STR);
        return new ItemCart(FakeValues.FAKE_LONG, product, FakeValues.FAKE_LONG, FakeValues.FAKE_DOUBLE, false);
    }

    @Test
    void when_get_by_id_then_return_item_cart() {
        final ItemCart itemCart = create();
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Mono.just(ItemCartMapper.INSTANCE.domainToEntity(itemCart)));

        var itemCartMono = service.getById(FakeValues.FAKE_LONG);

        StepVerifier.create(itemCartMono)
                .assertNext(itemCartFound -> {
                    assertEquals(itemCartFound, itemCart);
                });
    }

    @Test
    void when_get_by_inexistent_id_then_return_empty() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Mono.empty());
        var itemCartMono = service.getById(FakeValues.FAKE_LONG);

        StepVerifier.create(itemCartMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_create_item_cart_then_verify_repository_save() {
        final ItemCart itemCart = create();
        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(ItemCartMapper.INSTANCE.domainToEntity(itemCart)));

        var itemCartMono = service.create(itemCart);

        Mockito.verify(repository).save(itemCartArgumentCaptor.capture());
        ItemCartEntity itemCartArgumentCaptorValue = itemCartArgumentCaptor.getValue();

        StepVerifier.create(itemCartMono)
                .assertNext(itemCartCreated -> {
                    assertEquals(itemCartArgumentCaptorValue, itemCartCreated);
                });
    }

    @Test
    void when_update_existent_item_cart_then_verify_repository_save() {
        final ItemCart itemCart = create();
        final var itemCartToUpdate = ItemCart.builder()
                .price(5)
                .quantity(2)
                .picked(true)
                .product(Product.builder().id(2).build())
                .build();

        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Mono.just(ItemCartMapper.INSTANCE.domainToEntity(itemCart)));

        var itemCartMono = service.update(itemCartToUpdate.id(), itemCart);

        StepVerifier.create(itemCartMono)
                .assertNext(itemCartUpdated -> {
                    assertEquals(5, itemCartUpdated.price());
                    assertEquals(2, itemCartUpdated.price());
                    assertTrue(itemCartUpdated.picked());
                    assertEquals(2, itemCartUpdated.product().id());
                });
    }

    @Test
    void when_update_inexistent_item_cart_then_return_optional_of_empty() {
        final var itemCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Mono.empty());

        var itemCartMono = service.update(itemCart.id(), itemCart);

        StepVerifier.create(itemCartMono)
                .expectNextCount(0)
                .verifyComplete();
    }
}