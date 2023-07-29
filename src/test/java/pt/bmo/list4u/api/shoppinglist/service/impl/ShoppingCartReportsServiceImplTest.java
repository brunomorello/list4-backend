package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;
import pt.bmo.list4u.api.shoppinglist.repository.ReportsProductPriceTrendRepository;
import pt.bmo.list4u.api.shoppinglist.repository.ReportsTotalSpentByMonthRepository;
import pt.bmo.list4u.api.shoppinglist.utils.FakeValues;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartReportsServiceImplTest {

    @Mock
    private ReportsTotalSpentByMonthRepository totalSpentByMonthRepository;

    @Mock
    private ReportsProductPriceTrendRepository productPriceTrendRepository;

    @InjectMocks
    private ShoppingCartReportsServiceImpl service;

    @Test
    void when_getTotalSpentByMonthOnYear_then_return_values() {
        List<TotalSpentByMonthReport> totalSpentByMonthReports = Arrays.asList(createTotalSpentByMonthReport());
        Mockito.when(totalSpentByMonthRepository.getTotalSpentByMonthOnYear(Mockito.anyLong())).thenReturn(totalSpentByMonthReports);

        List<TotalSpentByMonthReport> totalSpentByMonthOnYear = service.getTotalSpentByMonthOnYear(FakeValues.LONG);

        assertFalse(totalSpentByMonthOnYear.isEmpty());
    }

    @Test
    void when_getProductsPriceTrends_then_return_values() {
        List<ProductPriceTrendReport> productPriceTrendReports = Arrays.asList(createProductPriceTrendReport());
        Mockito.when(productPriceTrendRepository.getProductsPriceTrends(FakeValues.LONG)).thenReturn(productPriceTrendReports);

        List<ProductPriceTrendReport> productsPriceTrends = service.getProductsPriceTrends(FakeValues.LONG);

        assertFalse(productsPriceTrends.isEmpty());
    }

    private TotalSpentByMonthReport createTotalSpentByMonthReport() {
        return new TotalSpentByMonthReport(FakeValues.LONG, FakeValues.DOUBLE);
    }

    private ProductPriceTrendReport createProductPriceTrendReport() {
        return new ProductPriceTrendReport(FakeValues.LONG, FakeValues.STRING, FakeValues.LONG, FakeValues.DOUBLE);
    }
}