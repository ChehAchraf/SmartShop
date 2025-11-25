package com.ecomm.smartshop.catalog.controller;

import com.ecomm.smartshop.catalog.dto.ProductRequest;
import com.ecomm.smartshop.catalog.dto.ProductResponse;
import com.ecomm.smartshop.catalog.service.interfaces.ProductService;
import com.ecomm.smartshop.shared.enums.UserRole;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> create (@RequestBody @Valid ProductRequest request,
                                                   HttpSession session){
        verifierAdmin(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> find(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getProductById(id));
    }



    private void verifierAdmin(HttpSession session){
        UserRole role = (UserRole) session.getAttribute("USER_ROLE");
        if(role != UserRole.ADMIN){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access refused : you must be an admin ");
        }
    }
}
