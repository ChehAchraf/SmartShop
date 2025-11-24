package com.ecomm.smartshop.sales.entity;


import com.ecomm.smartshop.billing.entity.Paiement;
import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.sales.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "commandes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String refCommande;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dateCreation = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private OrderStatus statut = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Pattern(regexp = "PROMO-[A-Z0-9]{4}", message = "the promo code must be like this PROMO-XXXX")
    private String codePromo;

    @Column(nullable = false)
    private Double sousTotal;

    @Column(nullable = false)
    @Builder.Default
    private Double remiseFidelite = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private Double remisePromo = 0.0;

    @Column(nullable = false)
    private Double montantHtApresRemise;

    @Column(nullable = false)
    private Double tva;

    @Column(nullable = false)
    private Double montantTotalTTC;

    @Column(nullable = false)
    private Double montantRestant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Paiement> paiements = new ArrayList<>();

}
