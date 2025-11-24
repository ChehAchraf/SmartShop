package com.ecomm.smartshop.billing.entity;

import com.ecomm.smartshop.billing.enums.PaymentMethod;
import com.ecomm.smartshop.billing.enums.PaymentStatus;
import com.ecomm.smartshop.sales.entity.Commande;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime datePaiement;

    private LocalDateTime dateEncaissement;

    private String reference;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
}
