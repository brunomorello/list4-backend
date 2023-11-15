package pt.bmo.list4u.api.shoppinglist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import pt.bmo.list4u.api.shoppinglist.entity.ShoppingCartEntity;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShoppingCartMapper {

    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    ShoppingCart entityToDomain(ShoppingCartEntity shoppingCartEntity);

    @InheritInverseConfiguration
    ShoppingCartEntity domainToEntity(ShoppingCart shoppingCart);
}
