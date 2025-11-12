package com.inetum.apigenerationloans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanSimulationResponse {
    private boolean approved;
    private double monthlyPayment;
    private double totalPayment;
    private String message;
}
