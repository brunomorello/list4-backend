package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://list4u-front.herokuapp.com/")
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ProductService service;

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> getById(@PathVariable long id) {
        LOGGER.info("getById: {}", id);
        return service.getById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping
    public Flux<Product> getAll() {
        LOGGER.info("getAll");
        return service.getAll();
    }

    @PostMapping
    Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("createProduct: requestBody= {}", product);
        return service.create(product)
                .map(productCreated -> {
                    URI uri = uriComponentsBuilder.path("/api/products/{id}").buildAndExpand(productCreated.id()).toUri();
                    return ResponseEntity.created(uri).body(product);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

    @PutMapping("{id}")
    Mono<ResponseEntity<Product>> updateProduct(@PathVariable long id, @RequestBody Product product) {
        LOGGER.info("updateProduct: pathVariable: {}, requestBody= {}", id, product);
        return service.update(id, product)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
