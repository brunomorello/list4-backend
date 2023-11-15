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
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;
import pt.bmo.list4u.api.shoppinglist.mapper.SupermarketMapper;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SupermarketServiceImplTest {

    @Mock
    private SupermarketRepository repository;

    @InjectMocks
    private SupermarketServiceImpl service;

    @Mock
    private Pageable pageable;

    @Captor
    private ArgumentCaptor<SupermarketEntity> supermarketArgumentCaptor;

    private Supermarket create() {
        return new Supermarket(FakeValues.FAKE_LONG, FakeValues.FAKE_STR, Country.BRAZIL);
    }

    @Test
    void when_get_supermarket_by_valid_id_then_return_supermarket() {
        Supermarket supermarket = create();
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Mono.just(SupermarketMapper.INSTANCE.domainToEntity(supermarket)));

        Mono<Supermarket> supermarketMono = service.getById(1l);

        StepVerifier.create(supermarketMono)
                    .expectNextCount(1)
                    .assertNext(supermarketFound -> {
                        assertEquals(FakeValues.FAKE_LONG, supermarketFound.id());
                        assertEquals(FakeValues.FAKE_STR, supermarketFound.name());
                        assertEquals(Country.BRAZIL, supermarketFound.country());
                    });
    }

    @Test
    void when_supermarket_not_found_return_empty() {
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Mono.empty());

        Mono<Supermarket> supermarketMono = service.getById(2l);

        StepVerifier.create(supermarketMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_get_all_then_return_pageable_of_supermarkets() {
        Flux<SupermarketEntity> supermarketEntityFlux = Flux.fromIterable(
                Arrays.asList(
                        SupermarketMapper.INSTANCE.domainToEntity(create()),
                        SupermarketMapper.INSTANCE.domainToEntity(create())
                )
        );
        Mockito.when(repository.findAll()).thenReturn(supermarketEntityFlux);

        Flux<Supermarket> supermarketFlux = service.getAll();

        StepVerifier.create(supermarketFlux)
                        .expectNextCount(2)
                        .assertNext(supermarket -> {
                            assertEquals(FakeValues.FAKE_LONG, supermarket.id());
                            assertEquals(FakeValues.FAKE_STR, supermarket.name());
                            assertEquals(Country.BRAZIL, supermarket.country());
                        });
    }

    @Test
    void when_no_supermarkets_then_get_all_returns_empty() {
        Mockito.when(repository.findAll()).thenReturn(Flux.empty());

        var supermarketFlux = service.getAll();

        StepVerifier.create(supermarketFlux)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_create_then_verify_repository_save() {
        final Supermarket supermarket = create();

        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(SupermarketMapper.INSTANCE.domainToEntity(supermarket)));

        var supermarketMono = service.create(supermarket);

        Mockito.verify(repository).save(supermarketArgumentCaptor.capture());
        Supermarket supermarketCreatedArgCaptor = SupermarketMapper.INSTANCE.entityToDomain(supermarketArgumentCaptor.getValue());

        StepVerifier.create(supermarketMono)
                .assertNext(supermarketCreated -> assertEquals(supermarketCreatedArgCaptor, supermarketCreated));
    }

    @Test
    void when_update_existent_supermarket_then_return_it() {
        final Supermarket supermarket = create();
        final Supermarket supermarketToUpdate = Supermarket.builder()
                .name("TEST")
                .country(Country.IRELAND)
                .build();

        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenReturn(Mono.just(SupermarketMapper.INSTANCE.domainToEntity(supermarket)));

        var supermarketMono = service.update(1l, supermarketToUpdate);

        StepVerifier.create(supermarketMono)
                    .assertNext(supermarketUpdated -> {
                        assertEquals(supermarketToUpdate, supermarketUpdated);
                        assertEquals(supermarketToUpdate.id(), supermarketUpdated.id());
                        assertEquals(supermarketToUpdate.name(), supermarketUpdated.name());
                        assertEquals(supermarketToUpdate.country(), supermarketUpdated.country());
                    });

    }

    @Test
    void when_update_inexistent_supermarket_then_return_empty() {
        final Supermarket supermarket = create();

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Mono.empty());
        var supermarketMono = service.update(supermarket.id(), supermarket);

        StepVerifier.create(supermarketMono)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void when_delete_existent_supermarket_then_return_it() {
        Mockito.when(repository.deleteById(Mockito.anyLong())).thenReturn(Mono.empty());

        Mono<Void> voidMono = service.delete(FakeValues.FAKE_LONG);

        StepVerifier.create(voidMono)
                .expectNextCount(0)
                .verifyComplete();
    }

}