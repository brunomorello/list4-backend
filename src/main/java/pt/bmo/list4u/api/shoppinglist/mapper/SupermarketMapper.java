package pt.bmo.list4u.api.shoppinglist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupermarketMapper {

    SupermarketMapper INSTANCE = Mappers.getMapper(SupermarketMapper.class);

    Supermarket entityToDomain(SupermarketEntity supermarket);

    @InheritInverseConfiguration
    SupermarketEntity domainToEntity(Supermarket supermarket);
}
