package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface ItemCartService {

//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    Mono<ItemCart> getById(Long id);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    Flux<ItemCart> getAll(Map<String, String> queryParams);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    Mono<ItemCart> create(ItemCart itemCart);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    Mono<ItemCart> update(long id, ItemCart itemCart);
}
