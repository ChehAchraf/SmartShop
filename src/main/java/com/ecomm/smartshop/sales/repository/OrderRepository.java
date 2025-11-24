package com.ecomm.smartshop.sales.repository;

import com.ecomm.smartshop.sales.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Commande,Long> {
}
