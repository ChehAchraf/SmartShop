package com.ecomm.smartshop.identity.dto;

import com.ecomm.smartshop.customer.enums.CustomerTier;

public record ClientResponse(
        Long id,
        String nom,
        String email,
        String phone,
        CustomerTier fidelityLevel,
        Integer totalOrders,
        Double totalSpent
) {
}
