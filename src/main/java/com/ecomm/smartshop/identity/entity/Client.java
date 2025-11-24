package com.ecomm.smartshop.identity.entity;

import com.ecomm.smartshop.customer.enums.CustomerTier;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clients")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "telephone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CustomerTier fidelityLevel = CustomerTier.BASIC;

    @Column(nullable = false)
    @Builder.Default
    private Integer totalOrders = 0;

    @Column(nullable = false)
    @Builder.Default
    private Double totalSpent = 0.0;


}
