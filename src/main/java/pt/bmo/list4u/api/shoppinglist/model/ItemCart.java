package pt.bmo.list4u.api.shoppinglist.model;

import lombok.Builder;

@Builder
public record ItemCart (
        long id,
        Product product,
        long quantity,
        double price,
        boolean picked
) { }
