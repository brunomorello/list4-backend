package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, Long> {
}
