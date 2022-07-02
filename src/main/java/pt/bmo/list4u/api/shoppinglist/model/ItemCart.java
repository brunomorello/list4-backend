package pt.bmo.list4u.api.shoppinglist.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class ItemCart {
    private long id;
    private Product product;
    private long quantity;
    private double price;
    private boolean picked;
}
