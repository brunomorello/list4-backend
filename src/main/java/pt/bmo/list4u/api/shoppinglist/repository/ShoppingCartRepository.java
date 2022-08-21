package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
