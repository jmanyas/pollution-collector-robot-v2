package com.seat.robot.pollution.collector.robot.service.util;

import org.junit.jupiter.api.Test;

import static org.testng.Assert.*;

public class PollutionLevelGeneratorTest {

    @Test
    public void testPollutionGeneratorLevel(){
        for (int i = 0; i < 100; i++) {
            assertTrue(PollutionLevelGenerator.generatePollutionLevel() < 201);
        }
    }

}