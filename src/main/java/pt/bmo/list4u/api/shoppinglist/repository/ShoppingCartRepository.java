package pt.bmo.list4u.api.shoppinglist.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.bmo.list4u.api.shoppinglist.entity.ShoppingCartEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface ShoppingCartRepository extends R2dbcRepository<ShoppingCartEntity, Long> {
    Flux<ShoppingCartEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    Flux<ShoppingCartEntity> findByFinished(boolean finished, Pageable pageable);

    @Query("""
        SELECT u.* FROM users u 
        JOIN role_users ru on u.id = ru.user_id 
        JOIN roles r on r.id = ru.role_id 
        WHERE u.phone_number=:phone AND role_name=:role
    """)
    Mono<ShoppingCartEntity> findByPhoneNumberAndRole(@Param("phone") String phoneNumber, @Param("role") String role);
}
