package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;

import java.util.List;

@Repository
public interface ReportsTotalSpentByMonthRepository extends JpaRepository<TotalSpentByMonthReport, Long> {

    @Query(value = "select extract(month from sc.created_at) as month, sum(price * quantity) as total\n" +
            " from item_cart ic \n" +
            " inner join product p ON (ic.product_id=p.id)\n" +
            " inner join shopping_cart_items sci on (ic.id = sci.items_id)\n" +
            " inner join shopping_cart sc on (sci.shopping_cart_id = sc.id)\n" +
            " where extract(year from sc.created_at) = ?1\n" +
            " group by extract(month from sc.created_at)", nativeQuery = true)
    List<TotalSpentByMonthReport> getTotalSpentByMonthOnYear(long year);

}
