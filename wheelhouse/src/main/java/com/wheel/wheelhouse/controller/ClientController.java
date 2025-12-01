package com.wheel.wheelhouse.controller;

import com.wheel.wheelhouse.dto.ClientDto;
import com.wheel.wheelhouse.dto.UserDto;
import com.wheel.wheelhouse.entity.Client;
import com.wheel.wheelhouse.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientDto clientDto) {
        Client createdClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    // Get by first name
    @GetMapping("/search/first-name")
    public ResponseEntity<List<Client>> getClientsByFirstName(
            @RequestParam String firstName) {
        List<Client> clients = clientService.getByFirstName(firstName);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(clients);
    }

    // Get by phone number
    @GetMapping("/search/phone")
    public ResponseEntity<Client> getClientByPhoneNumber(
            @RequestParam String phoneNumber) {
        Optional<Client> client = clientService.getByPhoneNumber(phoneNumber);
        return client.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Search by CIN
    @GetMapping("/search/cin")
    public ResponseEntity<Client> getClientByCin(@RequestParam String cin) {
        Optional<Client> client = clientService.getByCin(cin);
        return client.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update by CIN
    @PutMapping("/{cin}")
    public ResponseEntity<ClientDto> updateClient(
            @PathVariable String cin,
            @Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.updateClient(cin, clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    // Get all clients with pagination
    @GetMapping
    public ResponseEntity<Page<ClientDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientDto> clients = clientService.getAllClients(pageable);

        return ResponseEntity.ok(clients);
    }

    // Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }

}





























//    // Advanced search with multiple criteria
//    @GetMapping("/search")
//    public ResponseEntity<Page<Client>> searchClients(
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String phone,
//            @RequestParam(required = false) String city,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        // For now, return all clients since we don't have advanced search in service
//        // You can implement this in your service later
//        Page<Client> clients = clientService.getAllProducts(pageable);
//        return ResponseEntity.ok(clients);
//    }