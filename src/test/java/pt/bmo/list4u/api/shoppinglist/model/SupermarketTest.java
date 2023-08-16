package pt.bmo.list4u.api.shoppinglist.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Supermarket s1 = new Supermarket();
        s1.setId(supermarketId);
        s1.setName(supermarketName);
        s1.setCountry(Country.IRELAND);

        assertEquals(supermarketId, s1.getId(), "Id must be" + supermarketId);
        assertEquals(supermarketName, s1.getName(), "Name must be " + supermarketName);
        assertEquals(Country.IRELAND, s1.getCountry(), "Country must be IRELAND");
    }


    @Test
    void when_supermarket_created_by_constructor_used_then_check_gets() {
        assertEquals(supermarketId, supermarket.getId(), "Supermarket ID must be: " + supermarketId);
        assertEquals(supermarketName, supermarket.getName(), "Supermarket name must be: " + supermarketName);
        assertEquals(Country.BRAZIL, supermarket.getCountry(), "Supermarket country must be: " + Country.BRAZIL);
    }

    @Test
    void when_supermarket_has_same_id_and_name_then_equalsTo_is_true() {
        Supermarket s1 = new Supermarket(supermarketId, supermarketName, Country.BRAZIL);
        assertTrue(supermarket.equals(s1), "Both supermarket must be equals");
    }

    @Test
    void when_toString_then_value_is_got() {
        assertEquals("Supermarket(id=1, name=Supermarket Test, country=BRAZIL)", supermarket.toString());
    }
}