package com.ecomm.smartshop.catalog.controller;

import com.ecomm.smartshop.catalog.dto.ProductRequest;
import com.ecomm.smartshop.catalog.dto.ProductResponse;
import com.ecomm.smartshop.catalog.service.interfaces.ProductService;
import com.ecomm.smartshop.infrastructure.security.RequireRole;
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
    public ResponseEntity<ProductResponse> find(@PathVariable Long id,
                                                HttpSession session){
        verifierAdmin(session);
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getProductById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
    @PutMapping("/{id}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(service.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



    private void verifierAdmin(HttpSession session){
        UserRole role = (UserRole) session.getAttribute("USER_ROLE");
        if(role != UserRole.ADMIN){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access refused : you must be an admin ");
        }
    }
}
