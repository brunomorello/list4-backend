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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.ShoppingCartRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {

    @Mock
    private ShoppingCartRepository repository;

    @InjectMocks
    private ShoppingCartServiceImpl service;

    @Mock
    private Pageable pageable;

    @Captor
    private ArgumentCaptor<ShoppingCart> shoppingCartArgumentCaptor;

    @Test
    void when_get_by_existent_id_then_return_it() {
        ShoppingCart shoppingCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));

        Optional<ShoppingCart> optionalShoppingCart = service.getById(FakeValues.FAKE_LONG);

        assertTrue(optionalShoppingCart.isPresent());
    }

    @Test
    void when_get_by_inexistent_id_then_return_empty() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<ShoppingCart> optionalShoppingCart = service.getById(FakeValues.FAKE_LONG);

        assertTrue(optionalShoppingCart.isEmpty());
    }

    @Test
    void when_get_all_then_return_page_of_shopping_cart_items() {
        List<ShoppingCart> shoppingCartList = Arrays.asList(create());
        PageImpl<ShoppingCart> shoppingCartPage = new PageImpl<>(shoppingCartList);

        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(shoppingCartPage);

        Page<ShoppingCart> serviceGetAll = service.getAll(new HashMap<String, String>());

        assertFalse(serviceGetAll.isEmpty());
    }

    @Test
    void when_get_all_no_values_then_return_page_empty() {
        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<ShoppingCart> serviceGetAll = service.getAll(new HashMap<String, String>());

        assertTrue(serviceGetAll.isEmpty());
    }

    @Test
    void when_get_by_period_using_local_date_time_then_return_shopping_cart_items() {
        List<ShoppingCart> shoppingCartList = Arrays.asList(create());
        Mockito.when(repository.findByCreatedAtBetween(Mockito.any(), Mockito.any())).thenReturn(shoppingCartList);

        List<ShoppingCart> shoppingCarts = service.getByPeriod(LocalDateTime.now(), LocalDateTime.now());

        assertFalse(shoppingCarts.isEmpty());
    }

    @Test
    void when_get_by_period_using_local_date_time_then_return_empty_list() {
        Mockito.when(repository.findByCreatedAtBetween(Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());

        List<ShoppingCart> shoppingCarts = service.getByPeriod(LocalDateTime.now(), LocalDateTime.now());
        assertTrue(shoppingCarts.isEmpty());
    }

    @Test
    void when_get_by_period_using_params_then_return_cart_items() {
        List<ShoppingCart> shoppingCartList = Arrays.asList(create());
        Mockito.when(repository.findByCreatedAtBetween(Mockito.any(), Mockito.any())).thenReturn(shoppingCartList);

        List<ShoppingCart> shoppingCarts = service.getByPeriod("2023-07-20,2023-07-29");
        assertFalse(shoppingCarts.isEmpty());
    }

    @Test
    void when_get_by_period_using_params_then_return_empty_list() {
        List<ShoppingCart> shoppingCarts = service.getByPeriod(",");
        assertTrue(shoppingCarts.isEmpty());
    }

    @Test
    void when_get_all_finished_then_return_cart_items() {
        List<ShoppingCart> shoppingCartList = Arrays.asList(create());
        PageImpl<ShoppingCart> shoppingCartPage = new PageImpl<>(shoppingCartList);
        Mockito.when(repository.findByFinished(Mockito.anyBoolean(), (Pageable) Mockito.any())).thenReturn(shoppingCartPage);

        Page<ShoppingCart> allByFinished = service.getAllByFinished(false, pageable);
        assertFalse(allByFinished.isEmpty());
    }

    @Test
    void when_get_all_finished_then_empty_pageable() {
        Mockito.when(repository.findByFinished(Mockito.anyBoolean(), (Pageable) Mockito.any())).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<ShoppingCart> allByFinished = service.getAllByFinished(false, pageable);
        assertTrue(allByFinished.isEmpty());
    }

    @Test
    void when_create_then_verify_repository_save() {
        ShoppingCart shoppingCart = create();
        Mockito.when(repository.save(Mockito.any())).thenReturn(shoppingCart);

        ShoppingCart createdShoppingCart = service.create(shoppingCart);
        assertEquals(shoppingCart, createdShoppingCart);
    }

    @Test
    void when_update_existent_shopping_cart_then_return_it_and_verify_save_repository() {
        ShoppingCart shoppingCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(repository.save(Mockito.any())).thenReturn(shoppingCart);

        Optional<ShoppingCart> optionalShoppingCart = service.update(FakeValues.FAKE_LONG, shoppingCart);
        Mockito.verify(repository).save(shoppingCartArgumentCaptor.capture());

        assertEquals(optionalShoppingCart.get(), shoppingCartArgumentCaptor.getValue());
    }

    @Test
    void when_update_inexistent_shopping_cart_then_return_empty() {
        ShoppingCart shoppingCart = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<ShoppingCart> optionalShoppingCart = service.update(FakeValues.FAKE_LONG, shoppingCart);

        assertTrue(optionalShoppingCart.isEmpty());
    }

    @Test
    void when_delete_shopping_cart_then_verify_repository_delete() {
        service.delete(FakeValues.FAKE_LONG);
        Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(Mockito.anyLong());
    }

    private ShoppingCart create() {
        Product product = new Product(FakeValues.FAKE_LONG, FakeValues.FAKE_STR);
        List<ItemCart> itemCartList = Arrays.asList(new ItemCart(FakeValues.FAKE_LONG, product, FakeValues.FAKE_LONG, FakeValues.FAKE_DOUBLE, true, FakeValues.FAKE_STR));
        Supermarket supermarket = new Supermarket(FakeValues.FAKE_LONG, FakeValues.FAKE_STR, Country.BRAZIL);
        return new ShoppingCart(FakeValues.FAKE_LONG, FakeValues.FAKE_STR, itemCartList, false, supermarket, LocalDateTime.now());
    }
}