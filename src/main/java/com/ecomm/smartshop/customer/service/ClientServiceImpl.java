package com.ecomm.smartshop.customer.service;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;
import com.ecomm.smartshop.identity.entity.Client;
import com.ecomm.smartshop.customer.mapper.ClientMapper;
import com.ecomm.smartshop.identity.repository.ClientRepository;
import com.ecomm.smartshop.identity.repository.UserRepository;
import com.ecomm.smartshop.customer.service.interfaces.ClientService;
import com.ecomm.smartshop.shared.enums.UserRole;
import com.ecomm.smartshop.shared.exception.customized.BusinessException;
import com.ecomm.smartshop.shared.exception.customized.ResourceNotFoundException;
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
            throw new BusinessException("Email already exists");
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
                .toList();
    }

    @Override
    public ClientResponse getMyProfile(Long userId) {
        Client client = clientRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("profile not found"));
        return clientMapper.toResponse(client);
    }

    @Override
    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client not found"));
        return clientMapper.toResponse(client);
    }
}
