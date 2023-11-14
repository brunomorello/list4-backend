package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;

@Repository
public interface SupermarketRepository extends R2dbcRepository<SupermarketEntity, Long> {
}
