package com.inetum.apigenerationloans.service;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.exception.ClientNotFoundException;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.model.Loan;
import com.inetum.apigenerationloans.model.Simulation;
import com.inetum.apigenerationloans.repository.ClientRepository;
import com.inetum.apigenerationloans.repository.LoanRepository;
import com.inetum.apigenerationloans.repository.SimulationRepository;
import com.inetum.apigenerationloans.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
public class LoanService {

    //inyeccion de dependencias
    private final ClientRepository clientRepository;
    private final SimulationRepository loanSimulationRepository;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanService(ClientRepository clientRepository,
                       SimulationRepository loanSimulationRepository,
                       LoanRepository loanRepository,
                       LoanMapper loanMapper) {
        this.clientRepository = clientRepository;
        this.loanSimulationRepository = loanSimulationRepository;
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    //servicios

    public LoanDTO createLoanByClientIdAndSimulationId(Long clientId, Long simulationId) {

        //la simulacion se deberia obtener de una api externa
        Simulation simulation = loanSimulationRepository.findById(simulationId)
                .orElseThrow(() -> new NoSuchElementException("Simulation not found with ID: " + simulationId));

        // el CLientId se deberia obtener de la simulacion
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        //crear el prestamo
        Loan loan = new Loan();
        loan.setLoanAmount(simulation.getLoanAmount());
        loan.setInterestRate(simulation.getInterestRate());
        loan.setTerm(simulation.getTerm());
        loan.setInstallment(simulation.getInstallment());
        loan.setStatus(1); // activo
        loan.setCreationDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        loan.setClient(client);
        loan.setSimulation(simulation);
        loan.setPaymentSchedule(null);//*se debe generar el cronograma de pagos


        //guardar el prestamo
        loanRepository.save(loan);

        return loanMapper.toDTO(loan);
    }
}
