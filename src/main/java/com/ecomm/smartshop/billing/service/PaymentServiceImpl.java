package com.ecomm.smartshop.billing.service;

import com.ecomm.smartshop.billing.dto.PaymentRequest;
import com.ecomm.smartshop.billing.dto.PaymentResponse;
import com.ecomm.smartshop.billing.entity.Paiement;
import com.ecomm.smartshop.billing.enums.PaymentStatus;
import com.ecomm.smartshop.billing.mapper.PaymentMapper;
import com.ecomm.smartshop.billing.repository.PaymentRepository;
import com.ecomm.smartshop.billing.service.interfaces.PaymentService;
import com.ecomm.smartshop.customer.enums.CustomerTier;
import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.identity.repository.ClientRepository;
import com.ecomm.smartshop.sales.entity.Commande;
import com.ecomm.smartshop.sales.enums.OrderStatus;
import com.ecomm.smartshop.sales.repository.OrderRepository;
import com.ecomm.smartshop.shared.exception.customized.BusinessException;
import com.ecomm.smartshop.shared.exception.customized.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse addPayment(PaymentRequest request) {
        Commande commande = orderRepository.findById(request.commandeId())
                .orElseThrow(() -> new ResourceNotFoundException("commande is not found"));

        if (commande.getMontantRestant() <= 0) {
            throw new BusinessException("Cette commande est déjà payée intégralement !");
        }
        if (request.montant() > commande.getMontantRestant()) {
            throw new BusinessException("Le montant dépasse le reste à payer (" + commande.getMontantRestant() + " DH)");
        }
        Paiement paiement = paymentMapper.toEntity(request);
        paiement.setCommande(commande);
        paiement.setDatePaiement(LocalDateTime.now());

        int nextPaiementNumber = paymentRepository.findByCommandeId(commande.getId()).size() + 1;
        paiement.setNumeroPaiement(nextPaiementNumber);

        double nouveauReste = commande.getMontantRestant() - request.montant();
        commande.setMontantRestant(nouveauReste);

        if (nouveauReste == 0) {
            validerCommande(commande);
        }

        Paiement savedPayment = paymentRepository.save(paiement);
        orderRepository.save(commande);

        return paymentMapper.toResponse(savedPayment);
    }

    private void validerCommande(Commande commande) {
        commande.setStatut(OrderStatus.CONFIRMED);

        Client client = commande.getClient();

        client.setTotalOrders(client.getTotalOrders() + 1);
        client.setTotalSpent(client.getTotalSpent() + commande.getMontantTotalTTC());

        updateFidelityLevel(client);

        clientRepository.save(client);
    }

    private void updateFidelityLevel(Client client) {
        int orders = client.getTotalOrders();
        double spent = client.getTotalSpent();


        if (orders >= 20 || spent >= 15000) {
            client.setFidelityLevel(CustomerTier.PLATINUM);
        }
        else if (orders >= 10 || spent >= 5000) {
            client.setFidelityLevel(CustomerTier.GOLD);
        }
        else if (orders >= 3 || spent >= 1000) {
            client.setFidelityLevel(CustomerTier.SILVER);
        }
        else {
            client.setFidelityLevel(CustomerTier.BASIC);
        }
    }

    @Override
    public List<PaymentResponse> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByCommandeId(orderId).stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public void validatePayment(Long paymentId) {
        Paiement paiement = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable"));
        paiement.setStatut(PaymentStatus.ENCAISSE);
        paiement.setDateEncaissement(LocalDateTime.now());
        paymentRepository.save(paiement);
    }
}
