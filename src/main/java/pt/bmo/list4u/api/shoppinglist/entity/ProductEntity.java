package pt.bmo.list4u.api.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    private long id;
    private String name;
}
