package pt.bmo.list4u.api.shoppinglist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import pt.bmo.list4u.api.shoppinglist.entity.ProductEntity;
import pt.bmo.list4u.api.shoppinglist.model.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product entityToDomain(ProductEntity productEntity);

    @InheritInverseConfiguration
    ProductEntity domainToEntity(Product product);
}
