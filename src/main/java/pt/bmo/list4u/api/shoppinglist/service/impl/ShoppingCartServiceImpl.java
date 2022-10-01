package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.repository.ShoppingCartRepository;
import pt.bmo.list4u.api.shoppinglist.service.ShoppingCartService;

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
    public Optional<ShoppingCart> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Page<ShoppingCart> getAll(Map<String, String> queryParams) {
        return repository.findAll(Pageable.ofSize(10));
    }

    @Override
    public List<ShoppingCart> getByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<ShoppingCart> getByPeriod(String periodParams) {
        String[] periods = periodParams.split(",");
        if (Objects.nonNull(periods[0]) && Objects.nonNull(periods[1])) {
            LocalDateTime startDate = LocalDate.parse(periods[0], DateTimeFormatter.ISO_LOCAL_DATE).atTime(0,0);
            LocalDateTime endDate = LocalDate.parse(periods[1], DateTimeFormatter.ISO_LOCAL_DATE).atTime(0, 0);
            return repository.findByCreatedAtBetween(startDate, endDate);
        }
        return new ArrayList<>();
    }

    @Override
    public Page<ShoppingCart> getAllByFinished(boolean finished, Pageable pageable) {
        return repository.findByFinished(finished, pageable);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> update(long id, ShoppingCart shoppingCart) {
        if (repository.findById(id).isPresent()) {
            return Optional.of(repository.save(shoppingCart));
        }
        return Optional.empty();
    }
}
