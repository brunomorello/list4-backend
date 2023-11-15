package pt.bmo.list4u.api.shoppinglist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.model.report.ProductPriceTrendReport;
import pt.bmo.list4u.api.shoppinglist.model.report.TotalSpentByMonthReport;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartReportsService;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    ShoppingCartService service;

    @Autowired
    ShoppingCartReportsService reportsService;

    @GetMapping("{id}")
    public Mono<ResponseEntity<ShoppingCart>> getById(@PathVariable long id) {
        LOGGER.info("getById: " + id);

        return service.getById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping
    public Flux<ShoppingCart> getAllShoppingCarts(@RequestParam Map<String, String> queryParams, @PageableDefault(page = 0, size = 100) Pageable pageable) {
        LOGGER.info(queryParams.toString());

        if (queryParams.containsKey("byPeriod")) {
            return service.getByPeriod(queryParams.get("byPeriod"));
        }
        if (queryParams.containsKey("finished")) {
            return service.getAllByFinished(Boolean.valueOf(queryParams.get("finished")), pageable);
        }
        return service.getAll(queryParams);
    }

    @PostMapping
    public Mono<ResponseEntity<ShoppingCart>> createShoppingCart(@RequestBody ShoppingCart shoppingCartRequest, UriComponentsBuilder uriComponentsBuilder) {
        LOGGER.info("createShoppingCart: body= " + shoppingCartRequest);

        return service.create(shoppingCartRequest)
                .map(shoppingCart -> {
                    URI uri = uriComponentsBuilder.path("/api/shopping-carts/{id}").buildAndExpand(shoppingCart.id()).toUri();
                   return ResponseEntity.created(uri).body(shoppingCart);
                });
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ShoppingCart>> updateShoppingCart(@PathVariable long id, @RequestBody ShoppingCart shoppingCart) {
        LOGGER.info("updateShoppingCart: id= " + id);
        LOGGER.info("updateShoppingCart: body= " + shoppingCart);

        return service.update(id, shoppingCart)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteShoppingCart(@PathVariable long id) {
        LOGGER.info("deleteShoppingCart: id= {}", id);

        return service.delete(id);
    }

    @GetMapping("/total-spent-by-year/{year}")
    public Flux<TotalSpentByMonthReport> getTotalSpentByYear(@PathVariable("year") long year) {
        LOGGER.info("getTotalSpentByYear: {}", year);
        return reportsService.getTotalSpentByMonthOnYear(year);
    }

    @GetMapping("/products-trend-by-year/{year}")
    public Flux<ProductPriceTrendReport> getProductsTrendByYear(@PathVariable("year") long year) {
        LOGGER.info("getProductsTrendByYear: {}", year);
        return reportsService.getProductsPriceTrends(year);
    }
}
