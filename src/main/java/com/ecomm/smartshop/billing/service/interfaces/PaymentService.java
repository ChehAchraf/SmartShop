package com.ecomm.smartshop.billing.service.interfaces;

import com.ecomm.smartshop.billing.dto.PaymentRequest;
import com.ecomm.smartshop.billing.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse addPayment(PaymentRequest request);
    List<PaymentResponse> getPaymentsByOrderId(Long orderId);
    void validatePayment(Long paymentId);
}

