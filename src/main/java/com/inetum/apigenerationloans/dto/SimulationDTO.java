package com.inetum.apigenerationloans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationDTO {
    private Long id;
    private Double loanAmount;
    private String currency;
    private Double interestRate;
    private Integer term;
    private Double monthlyPayment;
    private Double totalPayment;
    private Boolean approved;
    private LocalDateTime createdAt;
    private Long clientId;
}
