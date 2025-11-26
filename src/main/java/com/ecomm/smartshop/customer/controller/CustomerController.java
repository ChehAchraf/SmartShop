package com.ecomm.smartshop.customer.controller;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;
import com.ecomm.smartshop.customer.service.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final ClientServiceImpl clientService;

    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<ClientResponse> update(@RequestBody ClientRequest request) {
        ClientResponse response = clientService.updateClient(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    
}

