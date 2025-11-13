package com.inetum.apigenerationloans.controller;

import com.inetum.apigenerationloans.dto.ClientDTO;
import com.inetum.apigenerationloans.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
    public List<Object> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        List<Object> response = new ArrayList<>();
        response.add("User created successfully");
        response.add(clientService.createClient(clientDTO));
        return response;
    }
}

