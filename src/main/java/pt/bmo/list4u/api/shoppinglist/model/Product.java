package pt.bmo.list4u.api.shoppinglist.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {
    private long id;
    private String name;
}
