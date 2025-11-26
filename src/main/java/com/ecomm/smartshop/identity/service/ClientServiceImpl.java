package com.ecomm.smartshop.identity.service;

import com.ecomm.smartshop.identity.dto.ClientRequest;
import com.ecomm.smartshop.identity.dto.ClientResponse;
import com.ecomm.smartshop.identity.repository.ClientRepository;
import com.ecomm.smartshop.identity.repository.UserRepository;
import com.ecomm.smartshop.identity.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Override
    public ClientResponse createClient(ClientRequest request) {
        return null;
    }

    @Override
    public ClientResponse updateClient(Long id, ClientRequest request) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }

    @Override
    public List<ClientResponse> getAllCLients() {
        return List.of();
    }

    @Override
    public ClientResponse getMyProfile(Long userId) {
        return null;
    }

    @Override
    public ClientResponse getClientById(Long id) {
        return null;
    }
}
