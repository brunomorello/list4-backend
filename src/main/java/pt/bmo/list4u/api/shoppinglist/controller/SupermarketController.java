package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.service.SupermarketService;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://list4u-front.herokuapp.com/")
@RequestMapping("/api/supermarkets")
public class SupermarketController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private SupermarketService service;

    @GetMapping("{id}")
    ResponseEntity<Supermarket> getById(@PathVariable long id) {
        LOGGER.info("Supermarkets - getById: {}", id);
        Optional<Supermarket> supermarketOptional = service.getById(id);
        return supermarketOptional.isPresent() ?
                ResponseEntity.ok(supermarketOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<Page<Supermarket>> getAll(Pageable pageable) {
        LOGGER.info("Supermarkets - getAll Pageable = {}", pageable);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PostMapping
    ResponseEntity createSupermarket(@RequestBody Supermarket supermarket, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("Supermarkets - createSupermarket(supermarket) = {}", supermarket);
        Supermarket supermarketCreated = service.create(supermarket);
        URI uri = uriComponentsBuilder.path("/api/supermarkets/{id}").buildAndExpand(supermarketCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(supermarketCreated);
    }

    @PutMapping("{id}")
    ResponseEntity updateSupermarket(@PathVariable long id, @RequestBody Supermarket supermarket) {
        LOGGER.info("Supermarkets - updateSupermarket({}) = {}", id, supermarket);
        supermarket.setId(id);
        Optional<Supermarket> supermarketOptional = service.update(supermarket);
        return supermarketOptional.isPresent() ?
                ResponseEntity.ok(supermarket) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity deleteSupermarket(@PathVariable long id) {
        LOGGER.info("Supermarkets - deleteSupermarket({})", id);
        return service.delete(id).isPresent() ?
            ResponseEntity.ok().build() :
            ResponseEntity.notFound().build();
    }
}
