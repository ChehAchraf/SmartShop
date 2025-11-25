package com.ecomm.smartshop.catalog.dto;

public record ProductResponse(
        Long id,
        String nom,
        String description,
        Double prixUnitaire,
        Integer stockDisponible
) {
}
