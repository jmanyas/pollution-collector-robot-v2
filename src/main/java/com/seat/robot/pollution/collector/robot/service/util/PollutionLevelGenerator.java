package com.seat.robot.pollution.collector.robot.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public final class PollutionLevelGenerator {

    private static final Logger log = LoggerFactory.getLogger(PollutionLevelGenerator.class);

    public static int generatePollutionLevel() {
        Random random = new Random();
        int pollutionLevel = random.nextInt(201);
        log.info("Pollution generated: " + pollutionLevel);
        return pollutionLevel;
    }
}
