package com.ecomm.smartshop.catalog.repository;

import com.ecomm.smartshop.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNomContainingIgnoreCase(String keyword);

}
