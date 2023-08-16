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
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    private ArgumentCaptor<Product> productArgumentCaptor;

    private Product create() {
        return new Product(FakeValues.FAKE_LONG, FakeValues.FAKE_STR);
    }

    @Test
    void when_get_by_id_then_return_product() {
        Product product = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));

        Optional<Product> optionalProduct = service.getById(FakeValues.FAKE_LONG);

        assertTrue(optionalProduct.isPresent());
    }

    @Test
    void when_get_by_inexistent_id_then_return_empty() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<Product> optionalProduct = service.getById(FakeValues.FAKE_LONG);

        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void when_create_product_then_verify_repository_save() {
        Product product = create();
        Mockito.when(repository.save(Mockito.any())).thenReturn(product);

        service.create(product);
        Mockito.verify(repository).save(productArgumentCaptor.capture());

        assertEquals(product, productArgumentCaptor.getValue());
    }

    @Test
    void when_update_existent_product_then_verify_repository_and_return_it() {
        Product product = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Mockito.when(repository.save(Mockito.any())).thenReturn(product);

        Optional<Product> optionalProduct = service.update(FakeValues.FAKE_LONG, product);
        Mockito.verify(repository).save(productArgumentCaptor.capture());

        assertEquals(optionalProduct.get(), productArgumentCaptor.getValue());
    }

    @Test
    void when_update_inexistent_product_then_return_empty() {
        Product product = create();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Optional<Product> optionalProduct = service.update(FakeValues.FAKE_LONG, product);

        assertTrue(optionalProduct.isEmpty());
    }
}