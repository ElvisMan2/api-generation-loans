package com.inetum.apigenerationloans.service;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.exception.ClientNotFoundException;
import com.inetum.apigenerationloans.model.Client;
import com.inetum.apigenerationloans.model.Loan;
import com.inetum.apigenerationloans.model.Payment;
import com.inetum.apigenerationloans.model.Simulation;
import com.inetum.apigenerationloans.repository.ClientRepository;
import com.inetum.apigenerationloans.repository.LoanRepository;
import com.inetum.apigenerationloans.repository.SimulationRepository;
import com.inetum.apigenerationloans.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Math.round;

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

    public LoanDTO generateLoanBySimulationId(Long simulationId) {

        //trae la simulacion de la base de datos
        Simulation simulation = loanSimulationRepository.findById(simulationId)
                .orElseThrow(() -> new NoSuchElementException("Simulation not found with ID: " + simulationId));

        //obteniendo el id del cliente desde la simulacion
        Long clientId = simulation.getClient().getClientId();

        //obteniendo el cliente desde la base de datos
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
        loan.setCurrency(simulation.getCurrency());
        loan.setClient(client);
        loan.setDisbursementDate(simulation.getDisbursementDate());
        loan.setSimulation(simulation);

        // Calcular cuotas del cronograma
        List<Payment> payments = generatePaymentSchedule(loan);
        loan.setPayment(payments); // Asignar pagos al préstamo

        loanRepository.save(loan); // Persistir el préstamo y pagos

        return loanMapper.toDTO(loan);
    }

    //funcion auxiliar para generar el cronograma de pagos
    private List<Payment> generatePaymentSchedule(Loan loan) {
        List<Payment> payments = new ArrayList<>();

        double principal = loan.getLoanAmount();           // P
        double annualRate = loan.getInterestRate();        // tasa anual
        int term = loan.getTerm();                         // n
        double monthlyRate = annualRate / 12 / 100;        // r

        String currency = loan.getCurrency();

        // Calcular cuota mensual (installment) con fórmula francesa
        double factor = Math.pow(1 + monthlyRate, term);
        double installment = principal * (monthlyRate * factor) / (factor - 1);
        installment = roundToTwoDecimals(installment); // cuota redondeada

        double balance = principal;
        LocalDate dueDate = loan.getDisbursementDate().plusMonths(1);

        LocalDate dueDateAux=null;//variable auxiliar para ajustar fechas

        double interest;
        double amortization;

        for (int i = 0; i < term; i++) {
            interest = roundToTwoDecimals(balance * monthlyRate);
            amortization = roundToTwoDecimals(installment - interest);
            balance = roundToTwoDecimals(balance - amortization);

            // Ajuste para que el último saldo quede exacto en cero si hay decimales flotantes
            if (i == term - 1) {
                amortization += balance;
                installment = roundToTwoDecimals(amortization + interest);
                balance = 0.0;
            }

            Payment payment = new Payment();
            payment.setPaymentNumber(i+1);
            payment.setInstallmentId(null); // Autogenerado
            payment.setCurrency(currency);
            payment.setInstallment(installment);
            payment.setAmortization(amortization);
            payment.setInterest(interest);
            payment.setCapitalBalance(balance);

            dueDateAux=dueDate.plusMonths(i);

            // Ajustar fecha si cae en fin de semana
            while (dueDateAux.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    dueDateAux.getDayOfWeek() == DayOfWeek.SUNDAY) {
                dueDateAux = dueDateAux.minusDays(1);
            }
            // Establecer la fecha de vencimiento ajustada
            payment.setDueDate(dueDateAux);

            payment.setLoan(loan);

            payments.add(payment);
        }

        return payments;
    }
    public static double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
