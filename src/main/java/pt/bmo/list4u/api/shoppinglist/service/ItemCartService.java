package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;

import java.util.Map;
import java.util.Optional;

@Service
public interface ItemCartService {
    public Optional<ItemCart> getById(Long id);
    public Optional<ItemCart> getAll(Map<String, String> queryParams);
    public ItemCart create(ItemCart itemCart);
    public Optional<ItemCart> update(ItemCart itemCart);
}
