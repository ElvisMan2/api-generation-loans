package com.inetum.apigenerationloans.controller;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.exception.ClientNotFoundException;
import com.inetum.apigenerationloans.exception.SimulationIdNotFoundException;
import com.inetum.apigenerationloans.model.Payment;
import com.inetum.apigenerationloans.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    // CASO FELIZ
    @Test
    public void testGenerateLoanBySimulationId_success() throws Exception {
        Long simulationId = 1L;

        LoanDTO mockLoanDTO = new LoanDTO();
        mockLoanDTO.setLoanId(100L);
        mockLoanDTO.setLoanAmount(5000.0);
        mockLoanDTO.setInterestRate(5.5);
        mockLoanDTO.setTerm(12);
        mockLoanDTO.setInstallment(430.0);
        mockLoanDTO.setStatus(1);
        mockLoanDTO.setCreationDate(LocalDateTime.now());
        mockLoanDTO.setCurrency("soles");
        mockLoanDTO.setDisbursementDate(LocalDate.of(2025, 11, 1));
        mockLoanDTO.setClientId(50L);

        Payment payment1 = new Payment();
        payment1.setInstallmentId(1L);
        payment1.setPaymentNumber(1);
        payment1.setCurrency("soles");
        payment1.setInstallment(430.0);
        payment1.setAmortization(400.0);
        payment1.setInterest(30.0);
        payment1.setDueDate(LocalDate.of(2025, 12, 1));
        payment1.setCapitalBalance(4600.0);

        mockLoanDTO.setPayment(List.of(payment1));

        when(loanService.generateLoanBySimulationId(simulationId)).thenReturn(mockLoanDTO);

        mockMvc.perform(post("/loans/generate/simulation/{simulationId}", simulationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").value(100))
                .andExpect(jsonPath("$.loanAmount").value(5000.0))
                .andExpect(jsonPath("$.term").value(12))
                .andExpect(jsonPath("$.payment[0].paymentNumber").value(1));
    }

    // CASO SIMULACIÓN NO ENCONTRADA
    @Test
    public void testGenerateLoanBySimulationId_simulationNotFound() throws Exception {
        Long simulationId = 4L;

        when(loanService.generateLoanBySimulationId(simulationId))
                .thenThrow(new SimulationIdNotFoundException(simulationId));

        mockMvc.perform(post("/loans/generate/simulation/{simulationId}", simulationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Simulation not found with ID: " + simulationId))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    // CASO CLIENTE NO ENCONTRADO
    @Test
    public void testGenerateLoanBySimulationId_clientNotFound() throws Exception {
        Long simulationId = 10L;

        when(loanService.generateLoanBySimulationId(simulationId))
                .thenThrow(new ClientNotFoundException(99L)); // Cliente faltante con ID 99

        mockMvc.perform(post("/loans/generate/simulation/{simulationId}", simulationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Client with ID 99 not found."))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.timestamp").exists());
    }


}
