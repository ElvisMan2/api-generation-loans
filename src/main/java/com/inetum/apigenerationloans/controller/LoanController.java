package com.inetum.apigenerationloans.controller;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.service.LoanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/loans")
public class LoanController {

    //inyeccion de dependencias
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    //generacion de prestamos a partir de una simulacion existente

    @PostMapping("/generate/simulation/{simulationId}")
    public ResponseEntity<LoanDTO> generateLoanBySimulationId(
            @PathVariable Long simulationId) {
        LoanDTO loanDTO = loanService.generateLoanBySimulationId( simulationId);
        return ResponseEntity.ok(loanDTO);
    }

}
