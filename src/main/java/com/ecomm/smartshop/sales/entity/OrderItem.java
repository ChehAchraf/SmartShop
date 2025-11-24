package com.ecomm.smartshop.sales.entity;

import com.ecomm.smartshop.catalog.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private double prixUnitaire;

    @Column(nullable = false)
    private  double totalLigne;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "commande_id") // Foreign Key
    private Commande commande;

    @PrePersist
    @PreUpdate
    public void calculerTotal(){
        this.totalLigne = this.quantite * this.prixUnitaire;
    }

}
