package com.ecomm.smartshop.sales.dto;

public record OrderItemResponse(
    Long productId,
    String productName, 
    int quantite,
    Double prixUnitaire,
    Double totalLigne
) {

}
