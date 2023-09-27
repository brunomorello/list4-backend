package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartReportsService;
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

    @Autowired
    ShoppingCartReportsService reportsService;

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
    ResponseEntity getAllShoppingCarts(@RequestParam Map<String, String> queryParams, @PageableDefault(page = 0, size = 100) Pageable pageable) {
        LOGGER.info(queryParams.toString());
        if (queryParams.containsKey("byPeriod")) {
            return ResponseEntity.ok(service.getByPeriod(queryParams.get("byPeriod")));
        }
        if (queryParams.containsKey("finished")) {
            return ResponseEntity.ok(service.getAllByFinished(Boolean.valueOf(queryParams.get("finished")), pageable));
        }
        return ResponseEntity.ok(service.getAll(queryParams));
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
        Optional<ShoppingCart> shoppingCartOptional = service.update(id, shoppingCart);
        if (shoppingCartOptional.isPresent()) {
            return ResponseEntity.ok(shoppingCartOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity deleteShoppingCart(@PathVariable long id) {
        LOGGER.info("deleteShoppingCart: id= {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total-spent-by-year/{year}")
    ResponseEntity getTotalSpentByYear(@PathVariable("year") long year) {
        LOGGER.info("getTotalSpentByYear: {}", year);
        return ResponseEntity.ok(reportsService.getTotalSpentByMonthOnYear(year));
    }

    @GetMapping("/products-trend-by-year/{year}")
    ResponseEntity getProductsTrendByYear(@PathVariable("year") long year) {
        LOGGER.info("getProductsTrendByYear: {}", year);
        return ResponseEntity.ok(reportsService.getProductsPriceTrends(year));
    }
}
