package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private final long id = 1l;
    private final String name = "Fake Shopping Cart";

    private final boolean finished = true;

    private static List<ItemCart> items = new ArrayList<>();

    private static Supermarket supermarket;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private static ShoppingCart shoppingCart;

    @BeforeEach
    void beforeEachTest() {
        items.clear();

        Product product = new Product(id, "Fake Product");
        ItemCart itemCart = new ItemCart(id, product, 2l, 1.5, true, "Fake Supermarket");
        supermarket = new Supermarket(id, "Fake Supermarket", Country.BRAZIL);
        items.add(itemCart);

        shoppingCart = new ShoppingCart(id, name, items, finished, supermarket, createdAt);
    }

    @Test
    void when_shopping_cart_is_created_and_sets_used_then_check_gets() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(id);
        shoppingCart1.setName(name);
        shoppingCart1.setItems(items);
        shoppingCart1.setFinished(finished);
        shoppingCart1.setCreatedAt(createdAt);

        assertEquals(id, shoppingCart1.getId(), "ID must be " + id);
        assertEquals(name, shoppingCart1.getName(), "Name must be" + name);
        assertEquals(items, shoppingCart1.getItems(), "Items list must be equals");
        assertEquals(finished, shoppingCart1.isFinished(), "Cart list is finished? " + finished);
        assertEquals(createdAt, shoppingCart1.getCreatedAt(), "Created At date must be " + createdAt);
    }

}