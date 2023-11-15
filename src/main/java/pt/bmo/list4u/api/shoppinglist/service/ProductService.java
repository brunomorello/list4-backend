package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProductService {
    Mono<Product> getById(Long id);
    Flux<Product> getAll();
    Mono<Product> create(Product product);
    Mono<Product> update(Long id, Product product);
}
