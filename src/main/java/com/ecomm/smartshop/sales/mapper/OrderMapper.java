package com.ecomm.smartshop.sales.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecomm.smartshop.sales.dto.OrderItemResponse;
import com.ecomm.smartshop.sales.dto.OrderResponse;
import com.ecomm.smartshop.sales.entity.Commande;
import com.ecomm.smartshop.sales.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "refCommande", source = "refCommande")
    OrderResponse toResponse(Commande commande);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.nom")
    OrderItemResponse toItemResponse(OrderItem orderItem);

}
