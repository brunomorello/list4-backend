package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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
        product = Product.builder()
                .id(id)
                .name("Fake Product")
        .build();

        itemCart = ItemCart.builder()
                .id(id)
                .product(product)
                .quantity(quantity)
                .price(price)
                .picked(picked)
        .build();
    }

    @Test
    void when_item_cart_is_created_and_sets_used_then_check_gets() {
        assertEquals(id, itemCart.id(), "ID must be " + id);
        assertEquals(product, itemCart.product(), "Product must be " + product);
        assertEquals(quantity, itemCart.quantity(), "Quantity must be " + quantity);
        assertEquals(price, itemCart.price(), "Price must be " + price);
        assertEquals(picked, itemCart.picked(), "Item Cart is picked must be " + picked);
    }

    @Test
    void when_item_cart_has_same_values_then_equalsTo_is_true() {
        var itemCart1 = ItemCart.builder()
                .id(id)
                .product(product)
                .quantity(quantity)
                .price(price)
                .picked(picked)
        .build();

        assertTrue(itemCart.equals(itemCart1));
    }

    @Test
    void when_toString_then_value_is_got() {
        final String itemCartStringfied = "ItemCart[id=1, product=Product[id=1, name=Fake Product], " +
                "quantity=2, price=1.5, picked=true]";
        assertEquals(itemCartStringfied, itemCart.toString());
    }
}