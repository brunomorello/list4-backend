package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends Page<ShoppingCart> {
    public Optional<ShoppingCart> getShoppingCartById(long id);
}
