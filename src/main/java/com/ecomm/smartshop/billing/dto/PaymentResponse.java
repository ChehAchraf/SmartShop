package com.ecomm.smartshop.billing.dto;

import com.ecomm.smartshop.billing.enums.PaymentMethod;
import com.ecomm.smartshop.billing.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Integer numeroPaiement,
        Double montant,
        PaymentMethod typePaiement,
        PaymentStatus statut,
        LocalDateTime datePaiement,
        LocalDateTime dateEncaissement,
        String reference,
        Double montantRestantCommande
) {
}
