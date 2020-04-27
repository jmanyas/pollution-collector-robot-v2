package com.seat.robot.pollution.collector.robot.model;

import java.util.Arrays;

public enum PollutionLevel {

    Good(0,50),
    Moderate(51, 100),
    USG(101,150),
    Unhealthy(150,200);

    private final double min;
    private final double max;

    PollutionLevel(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public static PollutionLevel get(double val) {
        return Arrays.stream(values())
                .filter(r -> val >= r.min && val <= r.max)
                .findFirst()
                .orElse(null);
    }

}
