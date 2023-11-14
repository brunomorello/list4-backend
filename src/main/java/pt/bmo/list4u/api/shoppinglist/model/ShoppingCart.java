package pt.bmo.list4u.api.shoppinglist.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ShoppingCart (
    long id,
    String name,
    List<ItemCart> items,
    boolean finished,
    Supermarket supermarket,
    LocalDateTime createdAt

) {}