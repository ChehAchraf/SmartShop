package com.ecomm.smartshop.sales.service.interfaces;

import java.util.List;

import com.ecomm.smartshop.sales.dto.CreateOrderRequest;
import com.ecomm.smartshop.sales.dto.OrderResponse;

public interface OrederService {
    OrderResponse createOrder(CreateOrderRequest request, Long clientId);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getMyOrders(Long clientId);
    List<OrderResponse> getAllOrders(); 
    void updateOrderStatus(Long orderId);
}
