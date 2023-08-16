package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        Product product = new Product(ID, "Fake Product");
        ItemCart itemCart = new ItemCart(ID, product, 2l, 1.5, true, "Fake Supermarket");
        supermarket = new Supermarket(ID, "Fake Supermarket", Country.BRAZIL);
        items.add(itemCart);

        shoppingCart = new ShoppingCart(ID, NAME, items, FINISHED, supermarket, createdAt);
    }

    @Test
    void when_shopping_cart_is_created_and_sets_used_then_check_gets() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(ID);
        shoppingCart1.setName(NAME);
        shoppingCart1.setItems(items);
        shoppingCart1.setFinished(FINISHED);
        shoppingCart1.setCreatedAt(createdAt);

        assertEquals(ID, shoppingCart1.getId(), "ID must be " + ID);
        assertEquals(NAME, shoppingCart1.getName(), "Name must be" + NAME);
        assertEquals(items, shoppingCart1.getItems(), "Items list must be equals");
        assertEquals(FINISHED, shoppingCart1.isFinished(), "Cart list is finished? " + FINISHED);
        assertEquals(createdAt, shoppingCart1.getCreatedAt(), "Created At date must be " + createdAt);
    }

    @Test
    void when_shopping_cart_is_created_and_constructor_has_been_used_then_check_gets() {
        assertNotNull(shoppingCart);

        assertEquals(ID, shoppingCart.getId(), "ID must be " + ID);
        assertEquals(NAME, shoppingCart.getName(), "Name must be " + NAME);
        assertEquals(items, shoppingCart.getItems(), "Items list must be equals");
        assertEquals(FINISHED, shoppingCart.isFinished(), "Shopping Cart List is finished? " + FINISHED);
        assertEquals(createdAt, shoppingCart.getCreatedAt(), "Created at must be " + createdAt);
    }

    @Test
    void when_shopping_cart_is_created_by_constructor_without_id_then_check_gets() {
        ShoppingCart shoppingCart1 = new ShoppingCart(NAME, items, FINISHED, supermarket, createdAt);

        assertEquals(NAME, shoppingCart1.getName(), "Name must be " + NAME);
        assertEquals(items, shoppingCart1.getItems(), "Items list must be equals");
        assertEquals(supermarket, shoppingCart1.getSupermarket(), "Supermarket must be equals");
        assertEquals(FINISHED, shoppingCart1.isFinished(), "Shopping Cart List is finished? " + FINISHED);
        assertEquals(createdAt, shoppingCart1.getCreatedAt(), "Created at must be " + createdAt);
    }

    @Test
    void when_shopping_cart_has_same_values_then_equalsTo_is_true() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(ID);
        shoppingCart1.setName(NAME);
        shoppingCart1.setItems(items);
        shoppingCart1.setFinished(FINISHED);
        shoppingCart1.setSupermarket(supermarket);
        shoppingCart1.setCreatedAt(createdAt);

        assertTrue(shoppingCart.equals(shoppingCart1));
    }

    @Test
    void when_toString_then_check_text_pattern() {
        final String shoppingCartStringfied = "ShoppingCart(id=1, name=Fake Shopping Cart, " +
                "items=[ItemCart(id=1, product=Product(id=1, name=Fake Product), quantity=2, price=1.5, picked=true, supermarketName=Fake Supermarket)], " +
                "finished=true, " +
                "supermarket=Supermarket(id=1, name=Fake Supermarket, country=BRAZIL), " +
                "createdAt=2023-07-29T10:00)";
        assertEquals(shoppingCartStringfied, shoppingCart.toString());
    }
}