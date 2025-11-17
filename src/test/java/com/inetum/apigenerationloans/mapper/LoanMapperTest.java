package com.inetum.apigenerationloans.mapper;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.mapper.LoanMapper;
import com.inetum.apigenerationloans.mapper.LoanMapperImpl;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.model.Loan;
import com.inetum.apigenerationloans.model.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanMapperTest {

    private final LoanMapper loanMapper = new LoanMapperImpl();

    @Test
    void testToDTO() {
        // GIVEN
        Client client = new Client();
        client.setClientId(101L);

        Payment payment1 = new Payment();
        payment1.setInstallment(850.0);

        Payment payment2 = new Payment();
        payment2.setInstallment(850.0);

        Loan loan = new Loan();
        loan.setLoanId(10L);
        loan.setLoanAmount(10000.0);
        loan.setInterestRate(10.5);
        loan.setTerm(12);
        loan.setInstallment(850.0);
        loan.setStatus(1);
        loan.setCreationDate(LocalDateTime.of(2025, 11, 15, 14, 0));
        loan.setCurrency("PEN");
        loan.setDisbursementDate(LocalDate.of(2025, 12, 1));
        loan.setClient(client);
        loan.setPayment(List.of(payment1, payment2));

        // WHEN
        LoanDTO dto = loanMapper.toDTO(loan);

        // THEN
        assertNotNull(dto);
        assertEquals(loan.getLoanId(), dto.getLoanId());
        assertEquals(loan.getLoanAmount(), dto.getLoanAmount());
        assertEquals(loan.getInterestRate(), dto.getInterestRate());
        assertEquals(loan.getTerm(), dto.getTerm());
        assertEquals(loan.getInstallment(), dto.getInstallment());
        assertEquals(loan.getStatus(), dto.getStatus());
        assertEquals(loan.getCreationDate(), dto.getCreationDate());
        assertEquals(loan.getCurrency(), dto.getCurrency());
        assertEquals(loan.getDisbursementDate(), dto.getDisbursementDate());
        assertEquals(client.getClientId(), dto.getClientId());

        assertNotNull(dto.getPayment());
        assertEquals(2, dto.getPayment().size());
        assertEquals(850.0, dto.getPayment().get(0).getInstallment());
    }
}

