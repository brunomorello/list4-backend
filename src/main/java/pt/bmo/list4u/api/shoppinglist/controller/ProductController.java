package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;

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
    ResponseEntity<Product> getById(@PathVariable long id) {
        LOGGER.info("getById: {}", id);
        Optional<Product> productOptional = service.getById(id);
        return productOptional.isPresent() ?
                ResponseEntity.ok(productOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<Product>> getAll() {
        LOGGER.info("getAll");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    ResponseEntity createProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("createProduct: requestBody= {}", product);
        Product productCreated = service.create(product);
        URI uri = uriComponentsBuilder.path("/api/products/{id}").buildAndExpand(productCreated.id()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("{id}")
    ResponseEntity updateProduct(@PathVariable long id, @RequestBody Product product) {
        LOGGER.info("updateProduct: pathVariable: {}, requestBody= {}", id, product);
        Optional<Product> productOptional = service.update(id, product);
        return productOptional.isPresent() ?
            ResponseEntity.ok(productOptional.get()) :
            ResponseEntity.status(404).body(null);
    }

}
