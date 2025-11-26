package com.ecomm.smartshop.sales.service;

import java.util.List;

import com.ecomm.smartshop.sales.dto.CreateOrderRequest;
import com.ecomm.smartshop.sales.dto.OrderResponse;
import com.ecomm.smartshop.sales.service.interfaces.OrederService;

public class OrderServiceImpl implements OrederService {

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById'");
    }

    @Override
    public List<OrderResponse> getMyOrders(Long clientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyOrders'");
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public void updateOrderStatus(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderStatus'");
    }

}
