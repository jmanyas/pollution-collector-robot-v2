package com.seat.robot.pollution.collector.robot;

import com.seat.robot.pollution.collector.robot.service.robot.RobotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PollutionCollectorRobotApplication implements CommandLineRunner {

    @Value("${path}")
    private String routePath;

    @Autowired
    RobotManager robotManager;

    public static void main(String[] args) {
        SpringApplication.run(PollutionCollectorRobotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        robotManager.setSpeed(3);
        robotManager.setPath(routePath);
        robotManager.startRobot();
    }
}