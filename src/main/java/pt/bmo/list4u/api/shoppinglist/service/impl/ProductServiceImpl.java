package pt.bmo.list4u.api.shoppinglist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.service.ProductService;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    public ProductRepository repository;

    public Optional<Product> getById(Long id) {
//        return repository.findById(id);
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
//        int pageNum = 0;
//        List<Product> productList = new ArrayList<>();
//
//        Page<Product> productPage = repository.findAll(Pageable.ofSize(100).withPage(pageNum));
//
//        while(productPage.hasContent()) {
//            List<Product> productListCollect = productPage.get().collect(Collectors.toList());
//            productList.addAll(productListCollect);
//            pageNum++;
//            productPage = repository.findAll(Pageable.ofSize(100).withPage(pageNum));
//        }
//
//        return productList;
        return null;
    }

    @Override
    public Product create(Product product) {
//        return repository.save(prepareProduct(product));
        return null;
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
//        if (repository.findById(id).isPresent()) {
//            product.setId(id);
//            return Optional.of(repository.save(prepareProduct(product)));
//        }
        return Optional.empty();
    }

}
