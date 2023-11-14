package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface SupermarketService {
    Mono<Supermarket> getById(long id);
    Flux<Supermarket> getAll();
    Mono<Supermarket> create(Supermarket supermarket);
    Mono<Supermarket> update(long id, Supermarket supermarket);
    Mono<Void> delete(long id);
}
