package com.ecomm.smartshop.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
    @NotNull(message = "L'ID du produit est obligatoire")
    Long productId,

    @Min(value = 1, message = "La quantité doit être au moins 1")
    int quantite
) {

}
