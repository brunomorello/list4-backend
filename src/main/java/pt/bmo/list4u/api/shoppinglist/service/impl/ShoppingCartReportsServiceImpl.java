package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;
import pt.bmo.list4u.api.shoppinglist.repository.ReportsProductPriceTrendRepository;
import pt.bmo.list4u.api.shoppinglist.repository.ReportsTotalSpentByMonthRepository;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartReportsService;
import reactor.core.publisher.Flux;

@Service
public class ShoppingCartReportsServiceImpl implements ShoppingCartReportsService {

    @Autowired
    private ReportsTotalSpentByMonthRepository totalSpentByMonthRepository;

    @Autowired
    private ReportsProductPriceTrendRepository productPriceTrendRepository;

    @Override
    public Flux<TotalSpentByMonthReport> getTotalSpentByMonthOnYear(long year) {
        return totalSpentByMonthRepository.getTotalSpentByMonthOnYear(year);
    }

    @Override
    public Flux<ProductPriceTrendReport> getProductsPriceTrends(long year) {
        return productPriceTrendRepository.getProductsPriceTrends(year);
    }
}
