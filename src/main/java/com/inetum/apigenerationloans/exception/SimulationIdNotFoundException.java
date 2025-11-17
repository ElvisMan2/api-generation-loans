package com.inetum.apigenerationloans.exception;

public class SimulationIdNotFoundException extends RuntimeException {
    public SimulationIdNotFoundException(Long simulationId) {
        super("Simulation not found with ID: " + simulationId);
    }
}
