package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;
import reactor.core.publisher.Flux;

@Repository
public interface ReportsTotalSpentByMonthRepository extends R2dbcRepository<TotalSpentByMonthReport, Long> {

    @Query(value = "select extract(month from sc.created_at) as month, sum(price * quantity) as total\n" +
            " from item_cart ic \n" +
            " inner join product p ON (ic.product_id=p.id)\n" +
            " inner join shopping_cart_items sci on (ic.id = sci.items_id)\n" +
            " inner join shopping_cart sc on (sci.shopping_cart_id = sc.id)\n" +
            " where extract(year from sc.created_at) = :year \n" +
            " group by extract(month from sc.created_at)")
    Flux<TotalSpentByMonthReport> getTotalSpentByMonthOnYear(@Param("year") long year);

}
