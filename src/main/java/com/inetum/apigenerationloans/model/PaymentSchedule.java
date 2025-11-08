package com.inetum.apisimulationloans.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "paymentSchedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long installmentId;

    private String currency; // "peruvian soles"
    private Double installment;
    private Double amortization;
    private Double interest;
    private LocalDate dueDate;
    private Double capitalBalance;

    @ManyToOne
    @JoinColumn(name="loan_id")
    private Loan loan;
}
