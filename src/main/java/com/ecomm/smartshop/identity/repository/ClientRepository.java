package com.ecomm.smartshop.identity.repository;

import com.ecomm.smartshop.identity.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
}
