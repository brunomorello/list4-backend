package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.entity.ProductEntity;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<ProductEntity, Long> {
    Mono<ProductEntity> findByName(String name);
}
