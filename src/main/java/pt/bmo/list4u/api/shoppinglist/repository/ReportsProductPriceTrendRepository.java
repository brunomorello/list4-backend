package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import reactor.core.publisher.Flux;

@Repository
public interface ReportsProductPriceTrendRepository extends R2dbcRepository<ProductPriceTrendReport, Long> {
    @Query(value = "select row_number() over (order by p.\"name\") as id, " +
            " upper(p.\"name\") as name, extract(month from sc.created_at) as month, ic.price\n" +
            " from item_cart ic \n" +
            " inner join product p on ic.product_id = p.id\n" +
            " inner join shopping_cart_items sci on (ic.id = sci.items_id)\n" +
            " inner join shopping_cart sc on (sci.shopping_cart_id = sc.id)\n" +
            " where ic.price > 0 \n" +
            " and extract(year from sc.created_at) = :year \n" +
            " order by 3, 2, 4")
    Flux<ProductPriceTrendReport> getProductsPriceTrends(@Param("year") long year);
}
