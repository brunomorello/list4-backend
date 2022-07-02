package pt.bmo.list4u.api.shoppinglist.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShoppingCart {
    private long id;
    private String name;
    private List<ItemCart> items;
    private boolean finished;
    private String country;
    private LocalDateTime createdAt;
}
