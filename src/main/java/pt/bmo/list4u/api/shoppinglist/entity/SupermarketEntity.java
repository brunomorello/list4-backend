package pt.bmo.list4u.api.shoppinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import pt.bmo.list4u.api.shoppinglist.model.Country;

@Data
@Builder
@Table("supermarket")
@AllArgsConstructor
@NoArgsConstructor
public class SupermarketEntity {
    @Id
    private long id;
    private Country country;
    private String name;
}
