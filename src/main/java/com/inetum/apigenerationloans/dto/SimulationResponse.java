package com.inetum.apigenerationloans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationResponse {
    private Long simulationId;
    private Double loanAmount;
    private String currency;
    private Double interestRate;
    private Integer term;
    private Double monthlyPayment;
    private Double totalPayment;
    private Boolean approved;
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate disbursementDate;
    private Long clientId;
}