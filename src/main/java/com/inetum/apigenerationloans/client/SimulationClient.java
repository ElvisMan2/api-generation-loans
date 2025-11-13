package com.inetum.apigenerationloans.client;

import com.inetum.apigenerationloans.dto.SimulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SimulationClient {

    private final RestTemplate restTemplate;

    public List<SimulationResponse> getSimulationsByClientId(Long clientId) {
        String url = "http://localhost:8081/api-simulation-loans/simulations/client/" + clientId;

        ResponseEntity<SimulationResponse[]> response = restTemplate.getForEntity(url, SimulationResponse[].class);
        SimulationResponse[] simulations = response.getBody();

        return simulations != null ? Arrays.asList(simulations) : List.of();
    }
}
