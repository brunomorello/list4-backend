package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.service.SupermarketService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<Supermarket>> getById(@PathVariable long id) {
        LOGGER.info("Supermarkets - getById: {}", id);
        return service.getById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping
    public Flux<Supermarket> getAll() {
        LOGGER.info("Supermarkets - getAll");
        return service.getAll();
    }

    @PostMapping
    public Mono<ResponseEntity<Supermarket>> createSupermarket(@RequestBody Supermarket supermarket, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("Supermarkets - createSupermarket(supermarket) = {}", supermarket);
        return service.create(supermarket)
                .map(supermarketCreated -> {
                    URI uri = uriComponentsBuilder.path("/api/supermarkets/{id}").buildAndExpand(supermarketCreated.id()).toUri();
                    return ResponseEntity.created(uri).body(supermarketCreated);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Supermarket>> updateSupermarket(@PathVariable long id, @RequestBody Supermarket supermarket) {
        LOGGER.info("Supermarkets - updateSupermarket({}) = {}", id, supermarket);
        return service.update(id, supermarket)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteSupermarket(@PathVariable long id) {
        LOGGER.info("Supermarkets - deleteSupermarket({})", id);
        return service.delete(id);
    }
}
