package pt.bmo.list4u.api.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("item_cart")
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartEntity {
    @Id
    private long id;
    private boolean picked;
    private double price;
    private long quantity;
    private long productId;
}
