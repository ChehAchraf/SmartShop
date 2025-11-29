package com.ecomm.smartshop;

import com.ecomm.smartshop.customer.enums.CustomerTier;
import com.ecomm.smartshop.identity.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogicFideliteTest {
    @Test
    void testUpgradeToSilver(){
        Client client = Client.builder()
                .fidelityLevel(CustomerTier.BASIC)
                .totalOrders(3)
                .totalSpent(900.0)
                .build();

        CustomerTier nouveauNiveau = calculerNiveau(client);
        Assertions.assertEquals(CustomerTier.SILVER, nouveauNiveau);
    }

    private CustomerTier calculerNiveau(Client client) {
        if (client.getTotalOrders() >= 20 || client.getTotalSpent() >= 15000) return CustomerTier.PLATINUM;
        if (client.getTotalOrders() >= 10 || client.getTotalSpent() >= 5000) return CustomerTier.GOLD;
        if (client.getTotalOrders() >= 3 || client.getTotalSpent() >= 1000) return CustomerTier.SILVER;
        return CustomerTier.BASIC;
    }
}
