package com.inetum.apigenerationloans.service;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.mapper.LoanMapper;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.model.Loan;
import com.inetum.apigenerationloans.model.Simulation;
import com.inetum.apigenerationloans.repository.ClientRepository;
import com.inetum.apigenerationloans.repository.LoanRepository;
import com.inetum.apigenerationloans.repository.SimulationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private SimulationRepository simulationRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanMapper loanMapper;

    @Test
    void testGenerateLoanBySimulationId_successful() {
        // GIVEN
        Long simulationId = 1L;
        Long clientId = 100L;

        Client client = new Client();
        client.setClientId(clientId);
        client.setFirstName("Juan");
        client.setMonthlyIncome(3000.0);

        Simulation simulation = new Simulation();
        simulation.setSimulationId(simulationId);
        simulation.setClient(client);
        simulation.setLoanAmount(10000.0);
        simulation.setInterestRate(12.0);
        simulation.setTerm(12);
        simulation.setInstallment(888.49);
        simulation.setCurrency("PEN");
        simulation.setDisbursementDate(LocalDate.of(2025, 12, 1));

        // Relacionar simulación con cliente
        client.setSimulations(List.of(simulation));

        Loan savedLoan = new Loan();
        savedLoan.setLoanId(200L);
        savedLoan.setLoanAmount(simulation.getLoanAmount());
        savedLoan.setInterestRate(simulation.getInterestRate());
        savedLoan.setTerm(simulation.getTerm());
        savedLoan.setInstallment(simulation.getInstallment());
        savedLoan.setStatus(1);
        savedLoan.setCurrency(simulation.getCurrency());
        savedLoan.setCreationDate(LocalDateTime.now());
        savedLoan.setDisbursementDate(simulation.getDisbursementDate());
        savedLoan.setClient(client);
        savedLoan.setSimulation(simulation);

        // Simulación tiene el préstamo creado
        simulation.setLoan(savedLoan);

        LoanDTO expectedDto = new LoanDTO();
        expectedDto.setLoanId(savedLoan.getLoanId());
        expectedDto.setLoanAmount(savedLoan.getLoanAmount());
        expectedDto.setInterestRate(savedLoan.getInterestRate());
        expectedDto.setTerm(savedLoan.getTerm());
        expectedDto.setInstallment(savedLoan.getInstallment());
        expectedDto.setStatus(savedLoan.getStatus());
        expectedDto.setCreationDate(savedLoan.getCreationDate());
        expectedDto.setCurrency(savedLoan.getCurrency());
        expectedDto.setDisbursementDate(savedLoan.getDisbursementDate());
        expectedDto.setClientId(clientId);
        expectedDto.setPayment(new ArrayList<>()); // simplificado

        // WHEN
        when(simulationRepository.findById(simulationId)).thenReturn(Optional.of(simulation));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);
        when(loanMapper.toDTO(any(Loan.class))).thenReturn(expectedDto);

        // ACT
        LoanDTO result = loanService.generateLoanBySimulationId(simulationId);

        // THEN
        assertNotNull(result);
        assertEquals(10000.0, result.getLoanAmount());
        assertEquals(12, result.getTerm());
        assertEquals("PEN", result.getCurrency());
        assertEquals(clientId, result.getClientId());

        verify(simulationRepository).findById(simulationId);
        verify(clientRepository).findById(clientId);
        verify(loanRepository).save(any(Loan.class));
        verify(loanMapper).toDTO(any(Loan.class));
    }
}

