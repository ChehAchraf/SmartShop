package com.ecomm.smartshop.billing.controller;


import com.ecomm.smartshop.billing.dto.PaymentRequest;
import com.ecomm.smartshop.billing.dto.PaymentResponse;
import com.ecomm.smartshop.billing.service.interfaces.PaymentService;
import com.ecomm.smartshop.infrastructure.security.RequireRole;
import com.ecomm.smartshop.shared.enums.UserRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<PaymentResponse> addPayment(@RequestBody @Valid PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.addPayment(request));
    }

    @GetMapping("/order/{orderId}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<List<PaymentResponse>> getPaymentsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentsByOrderId(orderId));
    }
}
