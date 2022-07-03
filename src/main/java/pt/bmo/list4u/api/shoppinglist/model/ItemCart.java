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
    private String supermakertName;

    public ItemCart(Product product, long quantity, double price, boolean picked, String supermakertName) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.picked = picked;
        this.supermakertName = supermakertName;
    }
}
