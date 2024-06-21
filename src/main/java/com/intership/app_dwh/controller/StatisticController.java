package com.intership.app_dwh.controller;

import com.intership.app_dwh.dto.CompanyStatistics;
import com.intership.app_dwh.service.StatisticService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/dwh/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{companyId}")
    public Mono<CompanyStatistics> getCompanyStatistics(@PathVariable UUID companyId) {
        return statisticService.getCompanyStatistics(companyId);
    }
}

