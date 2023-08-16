package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;
import pt.bmo.list4u.api.shoppinglist.service.SupermarketService;

import java.util.Optional;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    @Autowired
    private SupermarketRepository repository;

    @Override
    public Optional<Supermarket> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Supermarket> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Supermarket create(Supermarket supermarket) {
        return repository.save(supermarket);
    }

    @Override
    public Optional<Supermarket> update(Supermarket supermarket) {
        if (repository.findById(supermarket.getId()).isPresent()) {
            return Optional.of(repository.save(supermarket));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Supermarket> delete(long id) {
        Optional<Supermarket> supermarketOptional = repository.findById(id);
        if (supermarketOptional.isPresent()) {
            repository.deleteById(id);
            return supermarketOptional;
        }
        return Optional.empty();
    }
}
