package com.intership.app_dwh.service;

import com.intership.app_dwh.dto.CompanyStatistics;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class StatisticService {

    private final WebClient webClient;

    public StatisticService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://logist-service").build();
    }

    public Mono<CompanyStatistics> getCompanyStatistics(UUID companyId) {
        return webClient.get()
                .uri("/logist/statistics/{companyId}", companyId)
                .retrieve()
                .bodyToMono(CompanyStatistics.class);
    }
}
