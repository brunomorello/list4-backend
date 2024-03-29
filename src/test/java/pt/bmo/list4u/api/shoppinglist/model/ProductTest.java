package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private final long productId = 1l;
    private final String productName = "Product Test";

    private static Product product;

    @BeforeEach
    void beforeEachTest() {
        product = new Product(productId, productName);
    }

    @Test
    void when_product_is_created_and_sets_used_then_check_gets() {
        Product p1 = new Product();
        p1.setId(productId);
        p1.setName(productName);

        assertNotNull(p1);
        assertEquals(productId, p1.getId(),  "Product ID must be 1");
        assertEquals(productName, p1.getName(),  "Product Name must 'Product Test'");
    }

    @Test
    void when_product_is_created_and_constructor_used_then_check_gets() {
        assertNotNull(product);
        assertEquals(productId, product.getId(),  "Product ID must be 1");
        assertEquals(productName, product.getName(),  "Product Name must 'Product Test'");
    }

    @Test
    void when_product_is_created_only_with_name_then_check_gets() {
        Product p1 = new Product(productName);

        assertNotNull(p1);
        assertEquals(0, p1.getId(),  "Product ID must be 1");
        assertEquals(productName, p1.getName(),  "Product Name must 'Product Test'");
    }

    @Test
    void when_products_has_same_id_and_name_then_equalsTo_is_true() {
        Product p2 = new Product(productId, productName);

        assertTrue(product.equals(p2), "Product 1 must be equals to Product 2");
    }

    @Test
    void when_toString_then_value_is_got() {
        assertEquals("Product(id=1, name=Product Test)", product.toString());
    }


}