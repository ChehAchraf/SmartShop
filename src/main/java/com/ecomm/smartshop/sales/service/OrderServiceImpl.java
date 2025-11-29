package com.ecomm.smartshop.sales.service;

import com.ecomm.smartshop.catalog.entity.Product;
import com.ecomm.smartshop.catalog.repository.ProductRepository;
import com.ecomm.smartshop.customer.enums.CustomerTier;
import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.identity.repository.ClientRepository;
import com.ecomm.smartshop.infrastructure.security.RequireRole;
import com.ecomm.smartshop.sales.dto.CreateOrderRequest;
import com.ecomm.smartshop.sales.dto.OrderItemRequest;
import com.ecomm.smartshop.sales.dto.OrderResponse;
import com.ecomm.smartshop.sales.entity.Commande;
import com.ecomm.smartshop.sales.entity.OrderItem;
import com.ecomm.smartshop.sales.enums.OrderStatus;
import com.ecomm.smartshop.sales.mapper.OrderMapper;
import com.ecomm.smartshop.sales.repository.OrderRepository;
import com.ecomm.smartshop.sales.service.interfaces.OrederService;
import com.ecomm.smartshop.shared.enums.UserRole;
import com.ecomm.smartshop.shared.exception.customized.BusinessException;
import com.ecomm.smartshop.shared.exception.customized.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrederService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Commande commande = Commande.builder()
                .refCommande(UUID.randomUUID().toString())
                .client(client)
                .dateCreation(LocalDateTime.now())
                .statut(OrderStatus.PENDING)
                .items(new ArrayList<>())
                .build();

        double sousTotal = 0.0;

        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable: " + itemRequest.productId()));

            if (product.getStockDisponible() < itemRequest.quantity()) {
                throw new BusinessException("Stock insuffisant pour le produit: " + product.getNom());
            }

            product.setStockDisponible(product.getStockDisponible() - itemRequest.quantity());
            productRepository.save(product);


            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantite(itemRequest.quantity())
                    .prixUnitaire(product.getPrixUnitaire())
                    .commande(commande)
                    .build();

            orderItem.calculerTotal();
            commande.getItems().add(orderItem);

            sousTotal += orderItem.getTotalLigne();
        }
        commande.setSousTotal(sousTotal);

        double remiseFidelite = calculeRemiseFidelite(client, sousTotal);
        double remisePromo = calculeRemisePromo(request.codePromo(), sousTotal);

        commande.setRemiseFidelite(remiseFidelite);
        commande.setRemisePromo(remisePromo);
        commande.setCodePromo(request.codePromo());

        double montantHtApresRemise = sousTotal - (remiseFidelite + remisePromo);
        if (montantHtApresRemise < 0) montantHtApresRemise = 0;
        double tva = montantHtApresRemise * 0.20;
        double totalTtc = montantHtApresRemise + tva;

        commande.setMontantHtApresRemise(montantHtApresRemise);
        commande.setTva(tva);
        commande.setMontantTotalTTC(totalTtc);

        commande.setMontantRestant(totalTtc);
        Commande savedOrder = orderRepository.save(commande);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @RequireRole(UserRole.ADMIN)
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("commande not found"));    }

    @Override
    @RequireRole(UserRole.CLIENT)
    public List<OrderResponse> getMyOrders(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @RequireRole(UserRole.ADMIN)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    @RequireRole(UserRole.ADMIN)
    public void updateOrderStatus(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderStatus'");
    }

    private double calculeRemiseFidelite(Client client, double sousTotal) {
        CustomerTier tier = client.getFidelityLevel();


        if (tier == CustomerTier.SILVER && sousTotal > 500) return sousTotal * 0.05;
        if (tier == CustomerTier.GOLD && sousTotal > 800) return sousTotal * 0.10;
        if (tier == CustomerTier.PLATINUM && sousTotal > 1200) return sousTotal * 0.15;

        return 0.0;
    }

    private double calculeRemisePromo(String code, double sousTotal) {
        if (code != null && code.startsWith("PROMO-")) {
            return sousTotal * 0.05;
        }
        return 0.0;
    }

}
