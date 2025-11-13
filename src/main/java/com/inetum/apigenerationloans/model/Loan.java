package com.inetum.apigenerationloans.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Double loanAmount;
    private Double interestRate;
    private Integer term; // months
    private Double installment;
    private Integer status; // 1: active, 0: inactive
    private LocalDateTime creationDate;
    private String Currency; // "soles"

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(mappedBy = "loan")
    private Simulation simulation;

    @OneToMany(mappedBy ="loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payment = new ArrayList<>();
}
