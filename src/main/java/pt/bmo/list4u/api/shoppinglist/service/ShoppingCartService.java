package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ShoppingCartService {

    public Optional<ShoppingCart> getById(long id);
    public Page<ShoppingCart> getAll(Map<String, String> queryParams);
    public List<ShoppingCart> getByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    public List<ShoppingCart> getByPeriod(String periodParams);
    public Page<ShoppingCart> getAllByFinished(boolean finished, Pageable pageable);
    public ShoppingCart create(ShoppingCart shoppingCart);
    public Optional<ShoppingCart> update(long id, ShoppingCart shoppingCart);
}
