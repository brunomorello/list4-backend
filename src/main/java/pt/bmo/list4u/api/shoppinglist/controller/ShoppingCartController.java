package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ShoppingCartService service;

    @GetMapping("{id}")
    ResponseEntity<ShoppingCart> getById(@PathVariable long id) {
        LOGGER.info("getById: " + id);
        Optional<ShoppingCart> shoppingCartOptional = service.getById(id);
        if (shoppingCartOptional.isPresent()) {
            return ResponseEntity.ok(shoppingCartOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<Page<ShoppingCart>> getAllShoppingCarts(@RequestParam Map<String, String> queryParams) {
        LOGGER.info(queryParams.toString());
        return new ResponseEntity<Page<ShoppingCart>>(HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity createShoppingCart(@RequestBody ShoppingCart shoppingCartRequest, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("createShoppingCart: body= " + shoppingCartRequest);
        ShoppingCart shoppingCart = service.create(shoppingCartRequest);
        URI uri = uriComponentsBuilder.path("/api/shopping-carts/{id}").buildAndExpand(shoppingCart.getId()).toUri();
        return ResponseEntity.created(uri).body(shoppingCart);
    }

    @PutMapping("{id}")
    ResponseEntity updateShoppingCart(@PathVariable long id, @RequestBody ShoppingCart shoppingCart) {
        LOGGER.info("updateShoppingCart: id= " + id);
        LOGGER.info("updateShoppingCart: body= " + shoppingCart);
        Optional<ShoppingCart> shoppingCartOptional = service.update(shoppingCart);
        if (shoppingCartOptional.isPresent()) {
            return ResponseEntity.ok(shoppingCartOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
