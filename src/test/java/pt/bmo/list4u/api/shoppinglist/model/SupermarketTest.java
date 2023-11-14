package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {

    private final long supermarketId = 1l;
    private final String supermarketName = "Supermarket Test";

    private static Supermarket supermarket;

    @BeforeEach
    void beforeEachTest() {
        supermarket = new Supermarket(supermarketId, supermarketName, Country.BRAZIL);
    }

    @Test
    void when_supermarket_created_and_sets_used_then_check_gets() {
        assertEquals(supermarketId, supermarket.id(), "Id must be" + supermarketId);
        assertEquals(supermarketName, supermarket.name(), "Name must be " + supermarketName);
        assertEquals(Country.BRAZIL, supermarket.country(), "Country must be BRAZIL");
    }

    @Test
    void when_supermarket_has_same_id_and_name_then_equalsTo_is_true() {
        Supermarket s1 = new Supermarket(supermarketId, supermarketName, Country.BRAZIL);
        assertTrue(supermarket.equals(s1), "Both supermarket must be equals");
    }

    @Test
    void when_toString_then_value_is_got() {
        assertEquals("Supermarket[id=1, name=Supermarket Test, country=BRAZIL]", supermarket.toString());
    }
}