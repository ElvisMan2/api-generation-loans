package com.inetum.apigenerationloans.mapper;

import com.inetum.apigenerationloans.dto.ClientDTO;
import com.inetum.apigenerationloans.model.Client;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);

    ClientDTO toDto(Client entity);
}

