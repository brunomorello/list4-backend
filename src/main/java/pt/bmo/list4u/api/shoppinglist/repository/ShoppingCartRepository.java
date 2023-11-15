package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.entity.ShoppingCartEntity;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface ShoppingCartRepository extends R2dbcRepository<ShoppingCartEntity, Long> {
    Flux<ShoppingCartEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    Flux<ShoppingCartEntity> findByFinished(boolean finished, Pageable pageable);
}
