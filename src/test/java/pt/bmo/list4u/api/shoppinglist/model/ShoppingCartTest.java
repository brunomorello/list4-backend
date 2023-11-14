package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private static final long ID = 1l;
    private static final String NAME = "Fake Shopping Cart";

    private static final boolean FINISHED = true;

    private static List<ItemCart> items = new ArrayList<>();

    private static Supermarket supermarket;

    private final LocalDateTime createdAt = LocalDateTime.of(2023, 07, 29, 10, 00);

    private static ShoppingCart shoppingCart;

    @BeforeEach
    void beforeEachTest() {
        items.clear();

        final var product = Product.builder().id(ID).name("Fake Product").build();
        final var itemCart = ItemCart.builder()
                .id(ID)
                .product(product)
                .quantity(2l)
                .price(1.5)
                .picked(true)
                .build();

        supermarket = Supermarket.builder()
                .id(ID)
                .name("Fake Supermarket")
                .country(Country.BRAZIL)
        .build();

        items.add(itemCart);

        shoppingCart = new ShoppingCart(ID, NAME, items, FINISHED, supermarket, createdAt);
    }

    @Test
    void when_shopping_cart_is_created_and_sets_used_then_check_gets() {
        assertEquals(ID, shoppingCart.id(), "ID must be " + ID);
        assertEquals(NAME, shoppingCart.name(), "Name must be" + NAME);
        assertEquals(items, shoppingCart.items(), "Items list must be equals");
        assertEquals(FINISHED, shoppingCart.finished(), "Cart list is finished? " + FINISHED);
        assertEquals(createdAt, shoppingCart.createdAt(), "Created At date must be " + createdAt);
    }

    @Test
    void when_shopping_cart_has_same_values_then_equalsTo_is_true() {
        var shoppingCart1 = ShoppingCart.builder()
            .id(ID)
            .name(NAME)
            .items(items)
            .finished(FINISHED)
            .supermarket(supermarket)
            .createdAt(createdAt)
        .build();

        assertTrue(Objects.equals(shoppingCart, shoppingCart1));
    }

    @Test
    void when_toString_then_check_text_pattern() {
        final String shoppingCartStringfied = "ShoppingCart[id=1, name=Fake Shopping Cart, " +
                "items=[ItemCart[id=1, product=Product[id=1, name=Fake Product], quantity=2, price=1.5, picked=true]], " +
                "finished=true, " +
                "supermarket=Supermarket[id=1, name=Fake Supermarket, country=BRAZIL], " +
                "createdAt=2023-07-29T10:00]";
        assertEquals(shoppingCartStringfied, shoppingCart.toString());
    }
}