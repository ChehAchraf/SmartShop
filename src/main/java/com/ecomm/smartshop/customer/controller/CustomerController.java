package com.ecomm.smartshop.customer.controller;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;
import com.ecomm.smartshop.customer.service.ClientServiceImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class CustomerController {

    private final ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id,@RequestBody ClientRequest request) {
        ClientResponse response = clientService.updateClient(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse> delete(@PathVariable Long id) {
        ClientResponse response = clientService.getClientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> Cleints() {
        List<ClientResponse> response = clientService.getAllCLients();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/me")
    public ResponseEntity<ClientResponse> me() {
        ClientResponse response = clientService.getClientById(1L);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

