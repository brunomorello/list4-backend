package pt.bmo.list4u.api.shoppinglist.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceTrendReport {

    private long id;
    private String name;
    private long month;
    private double price;
}
