package com.seat.robot.pollution.collector.robot.service.robot;

import com.google.api.gbase.client.Location;
import com.google.maps.model.LatLng;
import com.seat.robot.pollution.collector.robot.model.PollutionInfo;
import com.seat.robot.pollution.collector.robot.service.distance.DistanceServiceFactory;
import com.seat.robot.pollution.collector.robot.service.reporting.ReportingManager;
import com.seat.robot.pollution.collector.robot.service.util.PollutionLevelGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RobotManagerBean implements RobotManager {

    private static final Logger log = LoggerFactory.getLogger(RobotManagerBean.class);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    private String path = "";
    private int speed = 1;

    private boolean stopRobot = Boolean.FALSE;

    private final DistanceServiceFactory factory;
    private final ReportingManager reportingManager;

    public RobotManagerBean(DistanceServiceFactory distanceServiceFactory, ReportingManager reportingManager) {
        this.factory = distanceServiceFactory;
        this.reportingManager = reportingManager;
    }

    @Override
    public void startRobot() throws InterruptedException {

        log.info("Starting Robot");

        stopRobot = Boolean.FALSE;
        int totalDistance = 0;

        List<LatLng> pathCoordinates = factory.getDistanceManager().getPathCoordinates(path);
        log.info("Total Steps to cover: " + (pathCoordinates.size() - 1));

        for (int i = 0; i < pathCoordinates.size() - 1; i++) {

            if (stopRobot) {
                break;
            }

            log.info("Step " + (i + 1) + " " + pathCoordinates.get(i) + " to -> " + pathCoordinates.get(i + 1));
            double pointDistance = factory.getDistanceManager().calculateDistance(pathCoordinates.get(i), pathCoordinates.get(i + 1));
            log.info("Distance Step " + (i + 1) + ": " + pointDistance);
            double timeToNextPoint = pointDistance / speed;
            log.info("Moving to next point in: " + timeToNextPoint + " seconds");
            Thread.sleep(TimeUnit.SECONDS.toMillis((long) timeToNextPoint));

            totalDistance += pointDistance;
            log.info(ANSI_BLACK_BACKGROUND + ANSI_WHITE + "Acumulated distance: " + totalDistance + " meters" + ANSI_RESET);
            if (totalDistance > 100) {
                reportingManager.addLectura(new PollutionInfo(new Location("Point", (float) pathCoordinates.get(i + 1).lat, (float) pathCoordinates.get(i + 1).lng),
                        PollutionLevelGenerator.generatePollutionLevel()));
                log.info(ANSI_RED + "Sending Lectura, total distance: " + totalDistance + ANSI_RESET);
                totalDistance = 0;
            }
        }

        log.info("End Robot");
    }

    @Override
    public int setSpeed(int speed) {
        log.info("Changing Speed to: " + speed);
        this.speed = speed;
        return this.speed;
    }

    @Override
    public void setPath(String path) {
        log.info("Changing path to: " + path);
        this.path = path;
    }

    @Override
    public void stopRobot() {
        log.info("Stopping Robot");
        stopRobot = Boolean.TRUE;
    }

}
