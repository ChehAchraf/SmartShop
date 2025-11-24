package com.ecomm.smartshop.identity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "User name is required")
        String username,

        @NotBlank(message = "password is required")
        String password
) {
}
