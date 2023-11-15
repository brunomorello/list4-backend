package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;
import reactor.core.publisher.Flux;

@Service
public interface ShoppingCartReportsService {

    Flux<TotalSpentByMonthReport> getTotalSpentByMonthOnYear(long year);

    Flux<ProductPriceTrendReport> getProductsPriceTrends(long year);
}
