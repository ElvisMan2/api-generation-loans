package com.inetum.apigenerationloans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Long loanId;//id del prestamo ya generado

    private Double loanAmount;//monto del prestamo||
    private Double interestRate;//porcentaje de interes
    private Integer term; //numero de meses
    private Double installment;//cuota mensual
    private Integer status;//si esta terminada de pagar o no
    private LocalDateTime creationDate;
    private Long clientId;//id del cliente al que pertenece el prestamo
}
