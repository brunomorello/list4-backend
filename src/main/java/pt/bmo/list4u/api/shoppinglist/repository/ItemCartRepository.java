package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.entity.ItemCartEntity;

@Repository
public interface ItemCartRepository extends R2dbcRepository<ItemCartEntity, Long> {
}
