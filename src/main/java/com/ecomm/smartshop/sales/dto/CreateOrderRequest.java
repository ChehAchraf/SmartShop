package com.ecomm.smartshop.sales.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record CreateOrderRequest(
    @NotEmpty(message = "The order must contain at least one item")
    List<OrderItemRequest> items,

    @Pattern(regexp = "PROMO-[A-Z0-9]{4}", message = "Invalid promo code format (PROMO-XXXX)")
    String codePromo
) {

}
    