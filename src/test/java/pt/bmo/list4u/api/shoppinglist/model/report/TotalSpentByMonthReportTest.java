package pt.bmo.list4u.api.shoppinglist.model.report;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalSpentByMonthReportTest {
    private static final long MONTH = 10l;
    private static final double TOTAL = 1002.31;
    private static TotalSpentByMonthReport totalSpentByMonthReport = new TotalSpentByMonthReport(MONTH, TOTAL);

    @Test
    void when_report_is_created_using_constructor_then_check_gets_values() {
        assertNotNull(totalSpentByMonthReport);

        assertEquals(MONTH, totalSpentByMonthReport.getMonth(), "Month must be " + MONTH);
        assertEquals(TOTAL, totalSpentByMonthReport.getTotal(), "Total must be " + TOTAL);
    }

    @Test
    void when_report_is_created_using_sets_then_check_gets_values() {
        TotalSpentByMonthReport totalSpentByMonthReport1 = new TotalSpentByMonthReport();
        totalSpentByMonthReport1.setMonth(MONTH);
        totalSpentByMonthReport1.setTotal(TOTAL);

        assertEquals(MONTH, totalSpentByMonthReport1.getMonth(), "Month must be " + MONTH);
        assertEquals(TOTAL, totalSpentByMonthReport1.getTotal(), "Total must be " + TOTAL);
    }
}