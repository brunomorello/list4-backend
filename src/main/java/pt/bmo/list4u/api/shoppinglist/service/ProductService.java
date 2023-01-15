package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    public Optional<Product> getById(Long id);
    public List<Product> getAll();
    public Product create(Product product);
    public Optional<Product> update(Long id, Product product);
}
