package com.ecomm.smartshop.customer.service.interfaces;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;

import java.util.List;

public interface ClientService {
    ClientResponse createClient(ClientRequest request);
    ClientResponse updateClient(Long id, ClientRequest request);
    void deleteClient(Long id);
    List<ClientResponse> getAllCLients();
    ClientResponse getMyProfile(Long userId);
    ClientResponse getClientById(Long id);

}
