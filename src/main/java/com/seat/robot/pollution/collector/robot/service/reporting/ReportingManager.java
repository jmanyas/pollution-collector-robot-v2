package com.seat.robot.pollution.collector.robot.service.reporting;

import com.seat.robot.pollution.collector.robot.model.PollutionInfo;
import com.seat.robot.pollution.collector.robot.model.Report;

import java.util.List;

public interface ReportingManager {
    void addLectura(PollutionInfo pollutionInfo);

    void initializeLecturas();

    Report generateReport(String source);

    List<PollutionInfo> getLecturas();
}
