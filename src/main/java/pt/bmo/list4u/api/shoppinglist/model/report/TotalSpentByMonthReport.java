package pt.bmo.list4u.api.shoppinglist.model.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalSpentByMonthReport {

    @Id
    private long month;
    private double total;
}
