package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;

import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository repository;

    @Override
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Product> getAll(Map<String, String> queryParams) {
        return Optional.empty();
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Optional<Product> update(Product product) {
        if (repository.findById(product.getId()).isPresent()) {
            return Optional.of(repository.save(product));
        }
        return Optional.empty();
    }
}
