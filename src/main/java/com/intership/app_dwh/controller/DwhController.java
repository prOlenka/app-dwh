package com.intership.app_dwh.controller;

import com.intership.app_dwh.dto.CompanyStatisticsDto;
import com.intership.app_dwh.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dwh/v1/statistics")
public class DwhController {
    private StatisticService statisticService;

    @GetMapping("/company")
    @PreAuthorize("hasAnyRole('LOGIST', 'ADMIN')")
    public ResponseEntity<CompanyStatisticsDto> getCompanyStatistics(@RequestParam UUID companyId) {
        return statisticService.getCompanyStatistics(companyId)
                .map(ResponseEntity::ok)
                .blockOptional()
                .orElse(ResponseEntity.notFound().build());
    }
}

