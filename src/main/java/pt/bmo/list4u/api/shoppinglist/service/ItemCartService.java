package pt.bmo.list4u.api.shoppinglist.service;

import org.springframework.stereotype.Service;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public interface ItemCartService {
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> getById(Long id);
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> getAll(Map<String, String> queryParams);
    @Transactional(Transactional.TxType.REQUIRED)
    public ItemCart create(ItemCart itemCart);
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<ItemCart> update(ItemCart itemCart);
}
