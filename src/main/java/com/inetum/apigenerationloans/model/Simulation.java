package com.inetum.apisimulationloans.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Data
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
    private Double installment;
    private Boolean acceptance;
    private LocalDateTime simulationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
