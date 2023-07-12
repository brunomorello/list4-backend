package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCartTest {

    private final long id = 1l;
    private final long quantity = 2l;

    private final double price = 1.5;

    private final boolean picked = true;

    private final String supermarketName = "Fake Supermarket";
    private static Product product;
    private static ItemCart itemCart;

    @BeforeEach
    void beforeEachTest() {
        product = new Product(id, "Fake Product");
        itemCart = new ItemCart(id, product, quantity, price, picked, supermarketName);
    }

    @Test
    void when_item_cart_is_created_and_sets_used_then_check_gets() {
        ItemCart itemCart1 = new ItemCart();
        itemCart1.setId(id);
        itemCart1.setProduct(product);
        itemCart1.setQuantity(quantity);
        itemCart1.setPrice(price);
        itemCart1.setPicked(picked);
        itemCart1.setSupermarketName(supermarketName);

        assertEquals(id, itemCart1.getId(), "ID must be " + id);
        assertEquals(product, itemCart1.getProduct(), "Product must be " + product);
        assertEquals(quantity, itemCart1.getQuantity(), "Quantity must be " + quantity);
        assertEquals(price, itemCart1.getPrice(), "Price must be " + price);
        assertEquals(picked, itemCart1.isPicked(), "Item Cart is picked must be " + picked);
        assertEquals(supermarketName, itemCart1.getSupermarketName(), "Supermarket Name must be " + supermarketName);
    }

    @Test
    void when_item_cart_is_created_and_constructor_used_then_check_gets() {
        assertNotNull(itemCart);

        assertEquals(id, itemCart.getId(), "ID must be " + id);
        assertEquals(product, itemCart.getProduct(), "Product must be " + product);
        assertEquals(quantity, itemCart.getQuantity(), "Quantity must be " + quantity);
        assertEquals(price, itemCart.getPrice(), "Price must be " + price);
        assertEquals(picked, itemCart.isPicked(), "Item Cart is picked must be " + picked);
        assertEquals(supermarketName, itemCart.getSupermarketName(), "Supermarket Name must be " + supermarketName);
    }

    @Test
    void when_item_cart_is_created_without_id_then_check_gets() {
        ItemCart itemCart1 = new ItemCart(product, quantity, price, picked, supermarketName);

        assertEquals(product, itemCart1.getProduct(), "Product must be " + product);
        assertEquals(quantity, itemCart1.getQuantity(), "Quantity must be " + quantity);
        assertEquals(price, itemCart1.getPrice(), "Price must be " + price);
        assertEquals(picked, itemCart1.isPicked(), "Item Cart is picked must be " + picked);
        assertEquals(supermarketName, itemCart1.getSupermarketName(), "Supermarket Name must be " + supermarketName);
    }

    @Test
    void when_item_cart_has_same_values_then_equalsTo_is_true() {
        ItemCart itemCart1 = new ItemCart();
        itemCart1.setId(id);
        itemCart1.setProduct(product);
        itemCart1.setQuantity(quantity);
        itemCart1.setPrice(price);
        itemCart1.setPicked(picked);
        itemCart1.setSupermarketName(supermarketName);

        assertTrue(itemCart.equals(itemCart1));
    }

    @Test
    void when_toString_then_value_is_got() {
        final String itemCartStringfied = "ItemCart(id=1, product=Product(id=1, name=Fake Product), " +
                "quantity=2, price=1.5, picked=true, supermarketName=Fake Supermarket)";
        assertEquals(itemCartStringfied, itemCart.toString());
    }
}