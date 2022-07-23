package pt.bmo.list4u.api.shoppinglist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCart> items;
    private boolean finished;
    private String country;
    private LocalDateTime createdAt;

    public ShoppingCart(String name, List<ItemCart> items, boolean finished, String country, LocalDateTime createdAt) {
        this.name = name;
        this.items = items;
        this.finished = finished;
        this.country = country;
        this.createdAt = createdAt;
    }
}
