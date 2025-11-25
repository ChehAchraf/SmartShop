package com.ecomm.smartshop.catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "the product name is required")
        String nom,

        String description,

        @NotNull
        @Positive(message = "the price most be positive")
        Double prixUnitaire,

        @NotNull
        @Min(value = 0, message = "Le stock ne peut pas être négatif")
        Integer stockDisponible

) {
}
