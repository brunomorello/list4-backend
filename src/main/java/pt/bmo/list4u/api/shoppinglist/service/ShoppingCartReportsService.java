package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;

import java.util.List;

@Service
public interface ShoppingCartReportsService {
    public List<TotalSpentByMonthReport> getTotalSpentByMonthOnYear(long year);
    public List<ProductPriceTrendReport> getProductsPriceTrends(long year);
}
