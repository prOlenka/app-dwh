package com.intership.app_dwh.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyStatistics {

    private int completedTrips;
    private int canceledTrips;
    private int startedTrips;
    private int tasksCount;
    private LocalDateTime fromDate;

}