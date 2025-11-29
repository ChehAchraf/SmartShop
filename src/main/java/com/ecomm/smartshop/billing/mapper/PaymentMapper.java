package com.ecomm.smartshop.billing.mapper;

import com.ecomm.smartshop.billing.dto.PaymentRequest;
import com.ecomm.smartshop.billing.dto.PaymentResponse;
import com.ecomm.smartshop.billing.entity.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numeroPaiement", ignore = true)
    @Mapping(target = "datePaiement", ignore = true)
    @Mapping(target = "dateEncaissement", ignore = true)
    @Mapping(target = "statut", constant = "EN_ATTENTE")
    @Mapping(target = "commande", ignore = true)
    Paiement toEntity(PaymentRequest request);

    @Mapping(target = "montantRestantCommande", source = "commande.montantRestant")
    PaymentResponse toResponse(Paiement paiement);
}
