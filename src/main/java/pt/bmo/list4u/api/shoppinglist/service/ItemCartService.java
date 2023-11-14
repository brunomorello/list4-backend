package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;

import java.util.Map;
import java.util.Optional;

@Service
public interface ItemCartService {

//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> getById(Long id);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> getAll(Map<String, String> queryParams);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    public ItemCart create(ItemCart itemCart);
//    @javax.transaction.Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> update(ItemCart itemCart);
}
