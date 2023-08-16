package pt.bmo.list4u.api.shoppinglist.model.report;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductPriceTrendReportTest {

    private static final long ID = 1l;
    private static final String NAME = "Fake Shopping Cart";

    private static final long MONTH = 10l;

    private static final double PRICE = 1.5;

    private static ProductPriceTrendReport productPriceTrendReport = create();

    @Test
    void when_report_is_created_using_sets_then_check_gets_values() {
        ProductPriceTrendReport productPriceTrendReport1 = new ProductPriceTrendReport();
        productPriceTrendReport1.setId(ID);
        productPriceTrendReport1.setName(NAME);
        productPriceTrendReport1.setMonth(MONTH);
        productPriceTrendReport1.setPrice(PRICE);

        assertEquals(ID, productPriceTrendReport1.getId(), "ID must be " + ID);
        assertEquals(NAME, productPriceTrendReport1.getName(), "Name must be " + NAME);
        assertEquals(MONTH, productPriceTrendReport1.getMonth(), "Month must be " + MONTH);
        assertEquals(PRICE, productPriceTrendReport1.getPrice(), "Price must be " + PRICE);
    }

    @Test
    void when_report_is_created_using_constructor_then_check_gets_values() {
        assertNotNull(productPriceTrendReport);

        assertEquals(ID, productPriceTrendReport.getId(), "ID must be " + ID);
        assertEquals(NAME, productPriceTrendReport.getName(), "Name must be " + NAME);
        assertEquals(MONTH, productPriceTrendReport.getMonth(), "Month must be " + MONTH);
        assertEquals(PRICE, productPriceTrendReport.getPrice(), "Price must be " + PRICE);
    }

    private static ProductPriceTrendReport create() {
        return new ProductPriceTrendReport(ID, NAME, MONTH, PRICE);
    }

}