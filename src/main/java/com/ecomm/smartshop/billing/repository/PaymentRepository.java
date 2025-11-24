package com.ecomm.smartshop.billing.repository;

import com.ecomm.smartshop.billing.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByCommandeId(Long commandeId);

}
