package com.intership.app_dwh.service;

import com.intership.app_dwh.dto.CompanyStatisticsDto;
import com.intership.app_dwh.entity.CompanyStatistics;
import com.intership.app_dwh.repository.CompanyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private WebClient webClient;
    private CompanyStatisticsRepository companyStatisticsRepository;

    public StatisticService(WebClient.Builder webClientBuilder, CompanyStatisticsRepository companyStatisticsRepository) {
        this.webClient = webClientBuilder.baseUrl("http://logist-service").build();
        this.companyStatisticsRepository = companyStatisticsRepository;
    }

    public Mono<CompanyStatisticsDto> getCompanyStatistics(UUID companyId) {
        return webClient.get()
                .uri("/logist/statistics/{companyId}", companyId)
                .retrieve()
                .bodyToMono(CompanyStatisticsDto.class)
                .doOnSuccess(companyStatisticsDto -> saveOrUpdateStatistics(companyId, companyStatisticsDto));
    }

    private void saveOrUpdateStatistics(UUID companyId, CompanyStatisticsDto companyStatisticsDto) {
        LocalDate currentDate = LocalDate.now();
        CompanyStatistics companyStatistics = companyStatisticsRepository.findByCompanyIdAndDate(companyId, currentDate)
                .orElseGet(() -> {
                    CompanyStatistics newStats = new CompanyStatistics();
                    newStats.setCompanyId(companyId);
                    newStats.setDate(currentDate);
                    return newStats;
                });

        companyStatistics.setCompletedTrips(companyStatisticsDto.getCompletedTrips());
        companyStatistics.setCancelledTrips(companyStatisticsDto.getCancelledTrips());
        companyStatistics.setStartedTrips(companyStatisticsDto.getStartedTrips());
        companyStatistics.setTasks(companyStatisticsDto.getTasks());

        companyStatisticsRepository.save(companyStatistics);
    }
}
