package com.seat.robot.pollution.collector.robot.service.reporting;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportCreator {

    private final ReportingManager reportingManager;

    public ReportCreator(ReportingManager reportingManager) {
        this.reportingManager = reportingManager;
    }

    @Scheduled(initialDelay = 180000, fixedRate = 180000)
    public void createReport() {
        System.out.println(reportingManager.generateReport("robot"));
    }
}
