package pt.bmo.list4u.api.shoppinglist.model;

import lombok.Builder;

@Builder
public record Supermarket (
    long id,
    String name,
    Country country

) {}
