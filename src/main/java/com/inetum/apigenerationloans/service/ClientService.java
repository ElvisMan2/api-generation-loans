package com.inetum.apigenerationloans.service;

import com.inetum.apigenerationloans.dto.ClientDTO;

import com.inetum.apigenerationloans.exception.ClientNotFoundException;
import com.inetum.apigenerationloans.mapper.ClientMapper;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ClientService {

    //inyecccion de dependencias:
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    //servicios

    public ClientDTO createClient(ClientDTO clientDTO) {

        Client client = clientMapper.toEntity(clientDTO);//lo convierto a entidad
        client.setCreationDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        return clientMapper.toDto(clientRepository.save(client));
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return clientMapper.toDto(client);
    }
}


