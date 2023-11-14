package pt.bmo.list4u.api.shoppinglist.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalSpentByMonthReport {

    private long month;
    private double total;
}
