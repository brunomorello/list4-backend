package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Product;

import java.util.Map;
import java.util.Optional;

@Service
public interface ProductService {
    public Optional<Product> getById(Long id);
    public Optional<Product> getAll(Map<String, String> queryParams);
    public Product create(Product product);
    public Optional<Product> update(Product product);
}
