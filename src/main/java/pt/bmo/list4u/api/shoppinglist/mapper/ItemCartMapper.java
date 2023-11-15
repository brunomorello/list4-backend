package pt.bmo.list4u.api.shoppinglist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import pt.bmo.list4u.api.shoppinglist.entity.ItemCartEntity;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemCartMapper {

    ItemCartMapper INSTANCE = Mappers.getMapper(ItemCartMapper.class);

    @Mapping(source = "productId", target = "product.id")
    ItemCart entityToDomain(ItemCartEntity itemCartEntity);

    @InheritInverseConfiguration
    ItemCartEntity domainToEntity(ItemCart itemCart);
}
