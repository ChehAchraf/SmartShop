package com.ecomm.smartshop.identity.service;

import com.ecomm.smartshop.identity.dto.ClientRequest;
import com.ecomm.smartshop.identity.dto.ClientResponse;
import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.identity.mapper.ClientMapper;
import com.ecomm.smartshop.identity.repository.ClientRepository;
import com.ecomm.smartshop.identity.repository.UserRepository;
import com.ecomm.smartshop.identity.service.interfaces.ClientService;
import com.ecomm.smartshop.shared.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse createClient(ClientRequest request) {

        if (userRepository.existsByUsername(request.email())){
            throw new RuntimeException("Email already exists");
        }
        Client client = clientMapper.toEntity(request);
        client.setUsername(request.email());
        client.setRole(UserRole.CLIENT);
        client.setPassword(request.password());

        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    public ClientResponse updateClient(Long id, ClientRequest request) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        Client clientToDelete = clientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User is not found"));

        clientRepository.delete(clientToDelete);
    }

    @Override
    public List<ClientResponse> getAllCLients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
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
