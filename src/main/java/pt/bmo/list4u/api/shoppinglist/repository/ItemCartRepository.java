package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;

@Repository
public interface ItemCartRepository extends PagingAndSortingRepository<ItemCart, Long> {
}
