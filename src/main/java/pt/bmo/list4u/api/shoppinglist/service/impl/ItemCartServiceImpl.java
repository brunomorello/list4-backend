package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.repository.ItemCartRepository;
import pt.bmo.list4u.api.shoppinglist.service.ItemCartService;

import java.util.Map;
import java.util.Optional;

public class ItemCartServiceImpl implements ItemCartService {

//    @Autowired
//    private ItemCartRepository repository;

    @Override
    public Optional<ItemCart> getById(Long id) {
//        return repository.findById(id);
        return Optional.empty();
    }

    @Override
    public Optional<ItemCart> getAll(Map<String, String> queryParams) {
        return Optional.empty();
    }

    @Override
    public ItemCart create(ItemCart itemCart) {
//        return repository.save(itemCart);
        return null;
    }

    @Override
    public Optional<ItemCart> update(ItemCart itemCart) {
//        if (repository.findById(itemCart.getId()).isPresent()) {
//            return Optional.of(repository.save(itemCart));
//        }
        return Optional.empty();
    }
}
