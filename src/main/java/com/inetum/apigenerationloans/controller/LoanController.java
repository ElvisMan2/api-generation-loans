package com.inetum.apigenerationloans.controller;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.model.Loan;
import com.inetum.apigenerationloans.service.LoanService;
import lombok.RequiredArgsConstructor;

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


    @PostMapping("/generate/client/{clientId}/simulation/{simulationId}")
    public ResponseEntity<LoanDTO> generateLoan(
            @PathVariable Long clientId,
            @PathVariable Long simulationId) {
        LoanDTO loanDTO = loanService.createLoanByClientIdAndSimulationId(clientId, simulationId);
        return ResponseEntity.ok(loanDTO);
    }
}
