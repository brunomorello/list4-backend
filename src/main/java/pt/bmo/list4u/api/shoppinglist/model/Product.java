package pt.bmo.list4u.api.shoppinglist.model;

import lombok.Builder;

@Builder
public record Product (
        long id,
        String name
) {}
