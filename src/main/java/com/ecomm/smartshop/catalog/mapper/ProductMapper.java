package com.ecomm.smartshop.catalog.mapper;

import com.ecomm.smartshop.catalog.dto.ProductRequest;
import com.ecomm.smartshop.catalog.dto.ProductResponse;
import com.ecomm.smartshop.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(ProductRequest request);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "isDeleted",constant = "false")
    Product toEntity(ProductRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(ProductRequest request, @MappingTarget Product product);

}
