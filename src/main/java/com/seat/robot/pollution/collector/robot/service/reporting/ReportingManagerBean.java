package com.seat.robot.pollution.collector.robot.service.reporting;

import com.seat.robot.pollution.collector.robot.model.PollutionInfo;
import com.seat.robot.pollution.collector.robot.model.PollutionLevel;
import com.seat.robot.pollution.collector.robot.model.Report;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class ReportingManagerBean implements ReportingManager {

    List<PollutionInfo> pollutionInfoList = new ArrayList<>();

    @Override
    public void addLectura(PollutionInfo pollutionInfo) {
        pollutionInfoList.add(pollutionInfo);
    }

    @Override
    public void initializeLecturas() {
        pollutionInfoList.clear();
    }

    @Override
    public Report generateReport(String source) {

        if (pollutionInfoList.size() == 0) {
            return null;
        }

        double average = 0;
        OptionalDouble optionalDouble = pollutionInfoList.stream().mapToInt(PollutionInfo::getLevel).average();

        if (optionalDouble.isPresent()) {
            average = optionalDouble.getAsDouble();
        }

        Report report = new Report(pollutionInfoList.get(pollutionInfoList.size() - 1).getLocation(), PollutionLevel.get(average).toString(), source);

        if (source.compareToIgnoreCase("robot") == 0) {
            pollutionInfoList.clear();
        }

        return report ;
    }

    @Override
    public List<PollutionInfo> getLecturas() {
        return pollutionInfoList;
    }
}
