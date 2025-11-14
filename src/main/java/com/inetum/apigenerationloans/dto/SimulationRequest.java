package com.inetum.apigenerationloans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRequest {
    @NotNull
    @Positive
    private Double loanAmount;

    @NotBlank
    private String currency;

    @NotNull
    @Positive
    private Double interestRate; // anual

    @NotNull
    @Min(1)
    private Integer term; // meses

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate disbursementDate;
}
