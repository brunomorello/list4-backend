package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.mapper.SupermarketMapper;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;
import pt.bmo.list4u.api.shoppinglist.service.SupermarketService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    @Autowired
    private SupermarketRepository repository;

    @Override
    public Mono<Supermarket> getById(final long id) {
        return repository.findById(id)
                .map(SupermarketMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Flux<Supermarket> getAll() {
        return repository.findAll()
                .map(SupermarketMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Mono<Supermarket> create(final Supermarket supermarket) {
        return repository.save(SupermarketMapper.INSTANCE.domainToEntity(supermarket))
                .map(SupermarketMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Mono<Supermarket> update(final long id, final Supermarket supermarket) {
        return repository.findById(id)
                .log()
                .flatMap(supermarketFound -> {
                    supermarketFound.setName(supermarket.name());
                    supermarketFound.setCountry(supermarket.country());
                    return repository.save(supermarketFound);
                })
                .log()
                .map(SupermarketMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Mono<Void> delete(final long id) {
        return repository.deleteById(id).log();
    }
}
