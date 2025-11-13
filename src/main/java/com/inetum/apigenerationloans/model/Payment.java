package com.inetum.apigenerationloans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "paymentSchedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long installmentId;

    private Integer paymentNumber;
    private String currency; // "soles"
    private Double installment;
    private Double amortization;
    private Double interest;
    private LocalDate dueDate;
    private Double capitalBalance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loan_id")
    @JsonIgnore
    private Loan loan;
}
