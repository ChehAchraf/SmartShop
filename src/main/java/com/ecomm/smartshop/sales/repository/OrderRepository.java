package com.ecomm.smartshop.sales.repository;

import com.ecomm.smartshop.sales.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByClientId(Long clientId);

    boolean existsByCodePromo(String codePromo);
}
