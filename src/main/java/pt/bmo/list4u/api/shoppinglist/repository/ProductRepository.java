package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
