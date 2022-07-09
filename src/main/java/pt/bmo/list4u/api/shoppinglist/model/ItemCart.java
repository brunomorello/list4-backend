package pt.bmo.list4u.api.shoppinglist.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
@Entity
public class ItemCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    private long quantity;
    private double price;
    private boolean picked;
    private String supermakertName;

    public ItemCart(Product product, long quantity, double price, boolean picked, String supermakertName) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.picked = picked;
        this.supermakertName = supermakertName;
    }
}
