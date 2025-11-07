package com.inetum.apigenerationloans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long clientId;

    private String firstName;
    private String paternalLastName;
    private String maternalLastName;
    private Double monthlyIncome;
}
