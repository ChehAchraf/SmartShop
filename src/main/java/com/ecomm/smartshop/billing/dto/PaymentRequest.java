package com.ecomm.smartshop.billing.dto;

import com.ecomm.smartshop.billing.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
        @NotNull(message = "commande Id is required")
        Long commandeId,

        @NotNull(message = "the amount is required")
        @Positive(message = "the amount must be positive")
        Double montant,

        @NotNull(message = "The payment type is required (ESPECES, CHEQUE, VIREMENT)")
        PaymentMethod typePaiement,


        String reference
) {
}
