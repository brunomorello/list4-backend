package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.util.Map;
import java.util.Optional;

@Service
public interface ShoppingCartService {

    public Optional<ShoppingCart> getById(long id);
    public Page<ShoppingCart> getAll(Map<String, String> queryParams);
    public ShoppingCart create(ShoppingCart shoppingCart);
    public ShoppingCart update(long id, ShoppingCart shoppingCart);
}
