package com.inetum.apigenerationloans.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simulationId;

    private Double loanAmount;
    private String currency;
    private Double interestRate;
    private Integer term; // months

    private Double installment;//se calcula segun loanAmaunt, currency, interesRate y term
    private Double totalPayment;
    private Boolean acceptance;// true si el installment menor que el 50% del ingreso mensual del cliente
    private LocalDateTime simulationDate;//fecha y hora de la simulacion

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
