package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.repository.ShoppingCartRepository;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;

import java.util.Map;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Override
    public Optional<ShoppingCart> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Page<ShoppingCart> getAll(Map<String, String> queryParams) {
        return null;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> update(ShoppingCart shoppingCart) {
        if (repository.findById(shoppingCart.getId()).isPresent()) {
            return Optional.of(repository.save(shoppingCart));
        }
        return Optional.empty();
    }
}
