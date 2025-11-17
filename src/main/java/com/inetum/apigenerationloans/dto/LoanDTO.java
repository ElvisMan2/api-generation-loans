package com.inetum.apigenerationloans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inetum.apigenerationloans.model.Payment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Long loanId;//id del prestamo ya generado

    @NotNull
    private Double loanAmount;//monto del prestamo
    private Double interestRate;//porcentaje de interes

    @Min(value = 1, message = "Term must be at least 1")
    private Integer term; //numero de meses
    private Double installment;//cuota mensual
    private Integer status;//si esta terminada de pagar o no
    private LocalDateTime creationDate;
    private String currency; // "soles"
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate disbursementDate;
    private Long clientId;//id del cliente al que pertenece el prestamo
    private List<Payment> payment;//lista de pagos a realizar
}
