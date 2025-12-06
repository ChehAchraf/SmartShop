package com.ecomm.smartshop.customer.controller;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;
import com.ecomm.smartshop.customer.service.ClientServiceImpl;
import com.ecomm.smartshop.infrastructure.security.RequireRole;
import com.ecomm.smartshop.shared.enums.UserRole;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid; // ضروري للـ Validation
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients") 
public class ClientController {

    private final ClientServiceImpl clientService;


    @PostMapping
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid ClientRequest request, HttpSession session) {
        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody @Valid ClientRequest request, HttpSession session) {
        ClientResponse response = clientService.updateClient(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
        clientService.deleteClient(id); 
        return ResponseEntity.noContent().build(); 
    }
    @GetMapping("/{id}")
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping
    @RequireRole(UserRole.ADMIN)
    public ResponseEntity<List<ClientResponse>> getAllClients(HttpSession session) {
        return ResponseEntity.ok(clientService.getAllCLients());
    }


    @GetMapping("/me")
    public ResponseEntity<ClientResponse> me(HttpSession session) {
        Long userId = (Long) session.getAttribute("USER_ID"); 
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Veuillez vous connecter");
        }
        return ResponseEntity.ok(clientService.getMyProfile(userId));
    }

    
}