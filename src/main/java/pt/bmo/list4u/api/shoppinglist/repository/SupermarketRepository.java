package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;

@Repository
public interface SupermarketRepository extends PagingAndSortingRepository<Supermarket, Long> {
}
