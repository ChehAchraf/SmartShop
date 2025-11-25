package com.ecomm.smartshop.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
        @NotBlank(message = "The name is required")
        String nom,

        @NotBlank(message = "The email is required")
        @Email(message = "email format is not correct")
        String email,

        @NotBlank(message = "The password is required")
        String password,

        String phone
) {
}
