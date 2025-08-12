package com.example.java_all.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MetricsService {

    private final WebClient webClient = WebClient.create("http://localhost:8086/actuator");

    public String getMetric(String metricName) {

        return webClient.get()
                .uri("/metrics/" + metricName)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

}

