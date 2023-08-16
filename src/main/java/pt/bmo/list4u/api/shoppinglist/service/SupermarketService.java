package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;

import java.util.Optional;

@Service
public interface SupermarketService {
    public Optional<Supermarket> getById(long id);
    public Page<Supermarket> getAll(Pageable pageable);
    public Supermarket create(Supermarket supermarket);
    public Optional<Supermarket> update(Supermarket supermarket);
    public Optional<Supermarket> delete(long id);
}
