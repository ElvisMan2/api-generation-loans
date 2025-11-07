package com.inetum.apigenerationloans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSchedule {
    private Long installmentId;
    private String currency; // "peruvian soles"
    private Double installment;
    private Double amortization;
    private Double interest;
    private LocalDate dueDate;
    private Double capitalBalance;
}
