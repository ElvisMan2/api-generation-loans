package com.inetum.apigenerationloans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private Long loanId;
    private Double loanAmount;
    private Double interestRate;
    private Integer term; // months
    private Double installment;
    private Integer status; // 1: active, 0: inactive
}
