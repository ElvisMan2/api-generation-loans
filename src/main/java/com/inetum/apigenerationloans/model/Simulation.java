package com.inetum.apigenerationloans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {
    private Long simulationId;
    private Double loanAmount;
    private String currency; // "peruvian soles"
    private Double interestRate;
    private Integer term; // months
    private Double installment;
    private Boolean acceptance;
    private LocalDateTime simulationDate;
}
