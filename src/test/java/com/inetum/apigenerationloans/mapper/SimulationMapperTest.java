package com.inetum.apigenerationloans.mapper;

import com.inetum.apigenerationloans.dto.SimulationResponse;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.model.Simulation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SimulationMapperTest {

    private final SimulationMapper simulationMapper = new SimulationMapperImpl();

    @Test
    void testToEntity() {
        // GIVEN
        SimulationResponse dto = new SimulationResponse();
        dto.setSimulationId(1L);
        dto.setLoanAmount(5000.0);
        dto.setCurrency("PEN");
        dto.setInterestRate(12.0);
        dto.setTerm(12);
        dto.setMonthlyPayment(450.0);
        dto.setTotalPayment(5400.0);
        dto.setApproved(true);
        dto.setCreatedAt(LocalDateTime.of(2025, 11, 1, 10, 0));
        dto.setDisbursementDate(LocalDate.of(2025, 12, 1));
        dto.setClientId(99L);

        // WHEN
        Simulation entity = simulationMapper.toEntity(dto);

        // THEN
        assertNotNull(entity);
        assertEquals(dto.getSimulationId(), entity.getSimulationId());
        assertEquals(dto.getLoanAmount(), entity.getLoanAmount());
        assertEquals(dto.getCurrency(), entity.getCurrency());
        assertEquals(dto.getInterestRate(), entity.getInterestRate());
        assertEquals(dto.getTerm(), entity.getTerm());
        assertEquals(dto.getMonthlyPayment(), entity.getInstallment());
        assertEquals(dto.getTotalPayment(), entity.getTotalPayment());
        assertEquals(dto.getApproved(), entity.getAcceptance());
        assertEquals(dto.getCreatedAt(), entity.getSimulationDate());
        assertEquals(dto.getDisbursementDate(), entity.getDisbursementDate());
        assertNotNull(entity.getClient());
        assertEquals(dto.getClientId(), entity.getClient().getClientId());
    }

    @Test
    void testToDto() {
        // GIVEN
        Client client = new Client();
        client.setClientId(99L);

        Simulation entity = new Simulation();
        entity.setSimulationId(2L);
        entity.setLoanAmount(10000.0);
        entity.setCurrency("USD");
        entity.setInterestRate(10.0);
        entity.setTerm(24);
        entity.setInstallment(470.5);
        entity.setTotalPayment(11292.0);
        entity.setAcceptance(false);
        entity.setSimulationDate(LocalDateTime.of(2025, 11, 5, 9, 30));
        entity.setDisbursementDate(LocalDate.of(2025, 12, 10));
        entity.setClient(client);

        // WHEN
        SimulationResponse dto = simulationMapper.toDto(entity);

        // THEN
        assertNotNull(dto);
        assertEquals(entity.getSimulationId(), dto.getSimulationId());
        assertEquals(entity.getLoanAmount(), dto.getLoanAmount());
        assertEquals(entity.getCurrency(), dto.getCurrency());
        assertEquals(entity.getInterestRate(), dto.getInterestRate());
        assertEquals(entity.getTerm(), dto.getTerm());
        assertEquals(entity.getInstallment(), dto.getMonthlyPayment());
        assertEquals(entity.getTotalPayment(), dto.getTotalPayment());
        assertEquals(entity.getAcceptance(), dto.getApproved());
        assertEquals(entity.getSimulationDate(), dto.getCreatedAt());
        assertEquals(entity.getDisbursementDate(), dto.getDisbursementDate());
        assertEquals(client.getClientId(), dto.getClientId());
    }
}

