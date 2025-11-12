package com.inetum.apigenerationloans.mapper;

import com.inetum.apigenerationloans.dto.SimulationDTO;
import com.inetum.apigenerationloans.model.Simulation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimulationMapper {

    @Mapping(source = "id", target = "simulationId")
    @Mapping(source = "monthlyPayment", target = "installment")
    @Mapping(source = "approved", target = "acceptance")
    @Mapping(source = "createdAt", target = "simulationDate")
    @Mapping(source = "clientId", target = "client.clientId")
    Simulation toEntity(SimulationDTO dto);

    @Mapping(source = "simulationId", target = "id")
    @Mapping(source = "installment", target = "monthlyPayment")
    @Mapping(source = "acceptance", target = "approved")
    @Mapping(source = "simulationDate", target = "createdAt")
    @Mapping(source = "client.clientId", target = "clientId")
    SimulationDTO toDto(Simulation entity);
}
