package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.mapper.ProductMapper;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository repository;

    public Mono<Product> getById(Long id) {
        return repository.findById(id)
                .map(ProductMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Flux<Product> getAll() {
        return repository.findAll()
                .map(ProductMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Mono<Product> create(Product product) {
        return repository.save(ProductMapper.INSTANCE.domainToEntity(prepareProduct(product)))
                .map(ProductMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Mono<Product> update(Long id, Product product) {
        return repository.findById(id)
                .flatMap(productFound -> {
                    productFound.setName(product.name());
                    return repository.save(productFound);
                })
                .map(ProductMapper.INSTANCE::entityToDomain)
                .log();
    }
    private Product prepareProduct(Product product) {
        return Product.builder()
                .id(product.id())
                .name(product.name().toUpperCase(Locale.ROOT))
                .build();
    }

}
