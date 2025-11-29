package com.ecomm.smartshop.sales.controller;

import com.ecomm.smartshop.infrastructure.security.RequireRole;
import com.ecomm.smartshop.sales.dto.CreateOrderRequest;
import com.ecomm.smartshop.sales.dto.OrderResponse;
import com.ecomm.smartshop.sales.service.OrderServiceImpl;
import com.ecomm.smartshop.shared.enums.UserRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl service;

    @PostMapping
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<OrderResponse> create(
            @RequestBody @Valid CreateOrderRequest request,
            @RequestParam Long clientId
            ){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(request,clientId));

    }

    @GetMapping
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
