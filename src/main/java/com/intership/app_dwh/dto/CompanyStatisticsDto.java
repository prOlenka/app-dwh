package com.intership.app_dwh.dto;

import lombok.Data;

@Data
public class CompanyStatisticsDto {
    private Integer completedTrips;
    private Integer cancelledTrips;
    private Integer startedTrips;
    private Integer tasks;
}
