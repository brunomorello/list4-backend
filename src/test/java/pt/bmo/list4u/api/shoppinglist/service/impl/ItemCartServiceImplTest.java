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
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ItemCartRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;

import java.util.HashMap;
import java.util.Optional;

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
    private ArgumentCaptor<ItemCart> itemCartArgumentCaptor;

    private ItemCart create() {
        Product product = new Product(FakeValues.FAKE_LONG, FakeValues.FAKE_STR);
        return new ItemCart(FakeValues.FAKE_LONG, product, FakeValues.FAKE_LONG, FakeValues.FAKE_DOUBLE, false, FakeValues.FAKE_STR);
    }

    @Test
    void when_get_by_id_then_return_item_cart() {
        ItemCart itemCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(itemCart));

        Optional<ItemCart> itemCartOptional = service.getById(FakeValues.FAKE_LONG);

        assertTrue(itemCartOptional.isPresent());
    }

    @Test
    void when_get_by_inexistent_id_then_return_empty() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<ItemCart> itemCartOptional = service.getById(FakeValues.FAKE_LONG);

        assertTrue(itemCartOptional.isEmpty());
    }

    @Test
    void when_get_all_and_method_not_implemented_then_return_empty() {
        Optional<ItemCart> optionalItemCart = service.getAll(new HashMap<String, String>());

        assertTrue(optionalItemCart.isEmpty());
    }

    @Test
    void when_create_item_cart_then_verify_repository_save() {
        ItemCart itemCart = create();
        Mockito.when(repository.save(Mockito.any())).thenReturn(itemCart);

        ItemCart itemCartCreated = service.create(itemCart);
        Mockito.verify(repository).save(itemCartArgumentCaptor.capture());

        ItemCart itemCartArgumentCaptorValue = itemCartArgumentCaptor.getValue();

        assertEquals(itemCartCreated, itemCartArgumentCaptorValue);
    }

    @Test
    void when_update_existent_item_cart_then_verify_repository_save() {
        ItemCart itemCart = create();
        Mockito.when(repository.findById(FakeValues.FAKE_LONG)).thenReturn(Optional.of(itemCart));
        Mockito.when(repository.save(Mockito.any())).thenReturn(itemCart);

        Optional<ItemCart> updatedItemCart = service.update(itemCart);
        Mockito.verify(repository).save(itemCartArgumentCaptor.capture());

        assertTrue(updatedItemCart.isPresent());
        assertEquals(updatedItemCart.get(), itemCartArgumentCaptor.getValue());
    }

    @Test
    void when_update_inexistent_item_cart_then_return_optional_of_empty() {
        ItemCart itemCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<ItemCart> optionalItemCart = service.update(itemCart);
        assertTrue(optionalItemCart.isEmpty());
    }
}