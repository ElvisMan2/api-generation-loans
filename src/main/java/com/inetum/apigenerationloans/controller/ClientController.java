package com.inetum.apigenerationloans.controller;

import com.inetum.apigenerationloans.dto.ClientDTO;
import com.inetum.apigenerationloans.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    //inyeccion de dependencias
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //controllers

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.getClientById(id);

        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {

        return ResponseEntity.ok(clientService.createClient(clientDTO));

    }
}

