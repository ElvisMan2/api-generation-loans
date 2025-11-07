package com.inetum.apigenerationloans.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @PostMapping("/create/{simulationId}")
    public ResponseEntity<Map<String, Object>> createLoan(@PathVariable("simulationId") Long simulationId) {
        // Crear estructura base
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Loan created successfully");

        // Crear loan principal
        Map<String, Object> loan = new HashMap<>();
        loan.put("loanId", 4);
        loan.put("customerId", 5);
        loan.put("simulationId", simulationId);
        loan.put("loanAmount", 12000);
        loan.put("interestRate", 6.5);
        loan.put("termMonths", 36);
        loan.put("installment", 366);
        loan.put("status", 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        loan.put("creationDate", LocalDateTime.of(2025, 11, 15, 13, 21, 6).format(dtf));
        loan.put("startDate", LocalDate.of(2025, 11, 15).toString());
        loan.put("endDate", LocalDate.of(2028, 11, 15).toString());

        // Crear customer
        Map<String, Object> customer = new HashMap<>();
        customer.put("customerId", 5);
        customer.put("firstName", "Lucas");
        customer.put("lastNameFather", "Anderson");
        customer.put("lastNameMother", "Reed");
        customer.put("monthlyIncome", 5500);
        loan.put("customer", customer);

        // Crear PaymentSchedule (hardcodeado con 3 cuotas de ejemplo)
        List<Map<String, Object>> paymentSchedule = new ArrayList<>();
        paymentSchedule.add(createPayment(1, 50.0, 316.0, 11500.0));
        paymentSchedule.add(createPayment(2, 47.0, 319.0, 11181.0));
        paymentSchedule.add(createPayment(3, 44.0, 322.0, 10859.0));
        loan.put("PaymentSchedule", paymentSchedule);

        // Agregar loan al response
        response.put("loan", loan);

        return ResponseEntity.ok(response);
    }

    private Map<String, Object> createPayment(int id, double interest, double amortization, double remainingDebt) {
        Map<String, Object> payment = new HashMap<>();
        payment.put("amortizationId", id);
        payment.put("interest", interest);
        payment.put("amortization", amortization);
        payment.put("remainingDebt", remainingDebt);
        return payment;
    }
}
