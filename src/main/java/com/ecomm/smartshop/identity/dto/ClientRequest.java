package com.ecomm.smartshop.identity.dto;

import com.ecomm.smartshop.customer.enums.CustomerTier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String password,

        String phone
) {
}
