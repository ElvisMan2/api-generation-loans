package com.inetum.apisimulationloans.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String firstName;
    private String paternalLastName;
    private String maternalLastName;
    private Double monthlyIncome;

    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Simulation> simulations=new ArrayList<>();

    @OneToMany(mappedBy ="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans=new ArrayList<>();
}

