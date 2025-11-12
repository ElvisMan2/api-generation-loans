package com.inetum.apigenerationloans.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String firstName;
    private String paternalLastName;
    private String maternalLastName;
    private String currencyOfIncome;
    private Double monthlyIncome;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Simulation> simulations=new ArrayList<>();

    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans=new ArrayList<>();
}

