package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public interface ShoppingCartService {

    Mono<ShoppingCart> getById(long id);
    Flux<ShoppingCart> getAll(Map<String, String> queryParams);
    Flux<ShoppingCart> getByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    Flux<ShoppingCart> getByPeriod(String periodParams);
    Flux<ShoppingCart> getAllByFinished(boolean finished, Pageable pageable);
    Mono<ShoppingCart> create(ShoppingCart shoppingCart);
    Mono<ShoppingCart> update(long id, ShoppingCart shoppingCart);
    Mono<Void> delete(long id);
}
