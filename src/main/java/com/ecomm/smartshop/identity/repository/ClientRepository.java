package com.ecomm.smartshop.identity.repository;

import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
}
