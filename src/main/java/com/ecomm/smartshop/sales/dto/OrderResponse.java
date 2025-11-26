package com.ecomm.smartshop.sales.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ecomm.smartshop.sales.enums.OrderStatus;

public record OrderResponse(
    Long id,
    String refCommande,
    LocalDateTime dateCreation,
    OrderStatus statut,
    Double sousTotal,
    Double remiseFidelite,
    Double remisePromo,
    Double tva,
    Double montantTotalTTC,
    Double montantRestant,
    List<OrderItemResponse> items
) {

}
