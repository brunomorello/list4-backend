package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private static final Logger LOGGER = LogManager.getLogger();

    @GetMapping("{id}")
    ResponseEntity<ShoppingCart> getById(@PathVariable long id) {
        LOGGER.info("getById: " + id);
        return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Page<ShoppingCart>> getAllShoppingCarts(@RequestParam Map<String, String> queryParams) {
        LOGGER.info(queryParams.toString());
        return new ResponseEntity<Page<ShoppingCart>>(HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        LOGGER.info("createShoppingCart: body= " + shoppingCart);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity updateShoppingCart(@PathVariable long id, @RequestBody ShoppingCart shoppingCart) {
        LOGGER.info("updateShoppingCart: id= " + id);
        LOGGER.info("updateShoppingCart: body= " + shoppingCart);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
