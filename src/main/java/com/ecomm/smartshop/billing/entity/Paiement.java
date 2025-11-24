package com.ecomm.smartshop.billing.entity;

import com.ecomm.smartshop.billing.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paiements")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroPaiement;

    @Column(nullable = false)
    private Double montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod typePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PaymentStatus statut = PaymentStatus.EN_ATTENTE;
}
