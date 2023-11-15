package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.mapper.ShoppingCartMapper;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.repository.ShoppingCartRepository;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Objects;
import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Override
    public Mono<ShoppingCart> getById(long id) {
        return repository.findById(id)
                .map(ShoppingCartMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Flux<ShoppingCart> getAll(Map<String, String> queryParams) {
        return repository.findAll()
                .map(ShoppingCartMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Flux<ShoppingCart> getByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByCreatedAtBetween(startDate, endDate)
                .map(ShoppingCartMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Flux<ShoppingCart> getByPeriod(String periodParams) {
        String[] periods = periodParams.split(",");
        if (periods.length > 0 && Objects.nonNull(periods[0]) && Objects.nonNull(periods[1])) {
            LocalDateTime startDate = LocalDate.parse(periods[0], DateTimeFormatter.ISO_LOCAL_DATE).atTime(0,0);
            LocalDateTime endDate = LocalDate.parse(periods[1], DateTimeFormatter.ISO_LOCAL_DATE).atTime(0, 0);
            return repository.findByCreatedAtBetween(startDate, endDate)
                    .map(ShoppingCartMapper.INSTANCE::entityToDomain)
                    .log();
        }
        return Flux.empty();
    }

    @Override
    public Flux<ShoppingCart> getAllByFinished(boolean finished, Pageable pageable) {
        return repository.findByFinished(finished, pageable)
                .map(ShoppingCartMapper.INSTANCE::entityToDomain)
                .log();
    }

    @Override
    public Mono<ShoppingCart> create(ShoppingCart shoppingCart) {
        return repository.save(ShoppingCartMapper.INSTANCE.domainToEntity(shoppingCart))
                .log()
                .map(ShoppingCartMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Mono<ShoppingCart> update(long id, ShoppingCart shoppingCart) {
        return repository.findById(id)
                .flatMap(shoppingCartEntityFound -> {
                    shoppingCartEntityFound.setName(shoppingCart.name());
                    shoppingCartEntityFound.setFinished(shoppingCart.finished());
                    shoppingCartEntityFound.setSupermarketId(shoppingCart.supermarket().id());
                    return repository.save(shoppingCartEntityFound).log();
                })
                .map(ShoppingCartMapper.INSTANCE::entityToDomain);
    }

    @Override
    public Mono<Void> delete(long id) {
        return repository.deleteById(id);
    }
}
