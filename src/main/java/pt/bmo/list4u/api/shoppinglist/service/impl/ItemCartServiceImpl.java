package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pt.bmo.list4u.api.shoppinglist.mapper.ItemCartMapper;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.repository.ItemCartRepository;
import pt.bmo.list4u.api.shoppinglist.service.ItemCartService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private ItemCartRepository repository;

    @Override
    public Mono<ItemCart> getById(Long id) {
        return repository.findById(id)
                .log()
                .map(ItemCartMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Flux<ItemCart> getAll(Map<String, String> queryParams) {
        return repository.findAll()
                .log()
                .map(ItemCartMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Mono<ItemCart> create(ItemCart itemCart) {
        return repository.save(ItemCartMapper.INSTANCE.domainToEntity(itemCart))
                .log()
                .map(ItemCartMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Mono<ItemCart> update(long id, ItemCart itemCart) {
        return repository.findById(id)
                .flatMap(itemCartEntity -> {
                    itemCartEntity.setProductId(itemCartEntity.getProductId());
                    itemCartEntity.setPicked(itemCartEntity.isPicked());
                    itemCartEntity.setPrice(itemCartEntity.getPrice());
                    itemCartEntity.setQuantity(itemCartEntity.getQuantity());
                    return repository.save(itemCartEntity);
                })
                .map(ItemCartMapper.INSTANCE::entityToDomain);
    }
}
