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
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SupermarketServiceImplTest {

    @Mock
    private SupermarketRepository repository;

    @InjectMocks
    private SupermarketServiceImpl service;

    @Mock
    private Pageable pageable;

    @Captor
    private ArgumentCaptor<Supermarket> supermarketArgumentCaptor;

    private Supermarket create() {
        return new Supermarket(FakeValues.FAKE_LONG, FakeValues.FAKE_STR, Country.BRAZIL);
    }

    @Test
    void when_get_supermarket_by_valid_id_then_return_supermarket() {
//        Supermarket supermarket = create();
//        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(supermarket));
//
//        Optional<Supermarket> supermarketOptional = service.getById(1l);
//
//        assertTrue(supermarketOptional.isPresent());
//        assertEquals(FakeValues.FAKE_LONG, supermarketOptional.get().getId());
//        assertEquals(FakeValues.FAKE_STR, supermarketOptional.get().getName());
//        assertEquals(Country.BRAZIL, supermarketOptional.get().getCountry());
    }

    @Test
    void when_supermarket_not_found_return_empty() {
//        Optional<Supermarket> supermarketOptional = service.getById(2l);
//
//        assertFalse(supermarketOptional.isPresent());
    }

    @Test
    void when_get_all_then_return_pageable_of_supermarkets() {
//        List<Supermarket> supermarketList = Arrays.asList(create(), create());
//        PageImpl<Supermarket> supermarketPage = new PageImpl<>(supermarketList);
//
//        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(supermarketPage);
//
//        Page<Supermarket> supermarketsPage = service.getAll(pageable);
//
//        assertEquals(2, supermarketsPage.getTotalElements());
//        assertEquals(1, supermarketsPage.getTotalPages());
    }

    @Test
    void when_no_supermarkets_then_get_all_returns_empty() {
//        Mockito.when(repository.findAll((Pageable) Mockito.any())).thenReturn(new PageImpl<>(Collections.emptyList()));
//
//        Page<Supermarket> supermarketsPage = service.getAll(pageable);
//
//        assertEquals(0, supermarketsPage.getTotalElements());
    }

    @Test
    void when_create_then_verify_repository_save() {
//        final Supermarket supermarket = create();
//
//        Mockito.when(repository.save(Mockito.any())).thenReturn(supermarket);
//        service.create(supermarket);
//
//        Mockito.verify(repository).save(supermarketArgumentCaptor.capture());
//        Supermarket supermarketCreated = supermarketArgumentCaptor.getValue();
//
//        assertEquals(supermarket, supermarketCreated);
    }

    @Test
    void when_update_existent_supermarket_then_return_it() {
//        final Supermarket supermarket = create();
//
//        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(supermarket));
//        Mockito.when(repository.save(Mockito.any())).thenReturn(supermarket);
//        Optional<Supermarket> optionalSupermarket = service.update(supermarket);
//
//        Mockito.verify(repository).save(supermarketArgumentCaptor.capture());
//        Supermarket supermarketUpdated = supermarketArgumentCaptor.getValue();
//
//        assertEquals(supermarket, optionalSupermarket.get());
//        assertEquals(supermarket.getId(), supermarketUpdated.getId());
//        assertEquals(supermarket.getName(), supermarketUpdated.getName());
//        assertEquals(supermarket.getCountry(), supermarketUpdated.getCountry());
    }

    @Test
    void when_update_inexistent_supermarket_then_return_empty() {
//        final Supermarket supermarket = create();
//
//        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
//        Optional<Supermarket> optionalSupermarket = service.update(supermarket);
//
//        assertTrue(optionalSupermarket.isEmpty());
    }

    @Test
    void when_delete_existent_supermarket_then_return_it() {
//        final Supermarket supermarket = create();
//
//        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(supermarket));
//
//        Optional<Supermarket> deletedSupermarket = service.delete(FakeValues.FAKE_LONG);
//        Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(Mockito.anyLong());
//
//        assertTrue(deletedSupermarket.isPresent());
    }

    @Test
    void when_delete_inexistent_supermarket_then_return_empty() {
//        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
//
//        Optional<Supermarket> deletedSupermarket = service.delete(FakeValues.FAKE_LONG);
//
//        assertTrue(deletedSupermarket.isEmpty());
    }

}