package pt.bmo.list4u.api.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import pt.bmo.list4u.api.shoppinglist.model.Country;

import java.time.LocalDateTime;

@Data
@Builder
@Table("shopping_cart")
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartEntity {
    @Id
    private long id;
    private String name;
    private long supermarketId;
    private boolean finished;
    private LocalDateTime createdAt;
}
