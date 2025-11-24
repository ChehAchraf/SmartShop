package com.ecomm.smartshop.identity.dto;

import com.ecomm.smartshop.shared.enums.UserRole;

public record AuthResponse(
        Long id,
        String username,
        UserRole role
) {
}
