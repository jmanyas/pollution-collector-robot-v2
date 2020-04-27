package com.seat.robot.pollution.collector.robot.web;

import com.seat.robot.pollution.collector.robot.model.PollutionInfo;
import com.seat.robot.pollution.collector.robot.model.Report;
import com.seat.robot.pollution.collector.robot.service.reporting.ReportingManager;
import com.seat.robot.pollution.collector.robot.service.robot.RobotManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
public class RobotController {

    private final RobotManager robotManager;
    private final ReportingManager reportingManager;

    public RobotController(RobotManager robotManager, ReportingManager reportingManager) {
        this.robotManager = robotManager;
        this.reportingManager = reportingManager;
    }

    @GetMapping("/report")
    public Report report() {
        return reportingManager.generateReport("Web");
    }

    @PutMapping("/speed/{speed}")
    public int setSpeed(@PathVariable("speed") @Max(3) @Min(1) int speed) {
        return robotManager.setSpeed(speed);
    }

    @GetMapping("/lecturas")
    public List<PollutionInfo> getLectura() {
        return reportingManager.getLecturas();
    }

    @PutMapping("/stop")
    public void stopRobot() {
        robotManager.stopRobot();
    }

    @PutMapping("/start")
    public void startRobot() throws InterruptedException {
        robotManager.startRobot();
    }

}
