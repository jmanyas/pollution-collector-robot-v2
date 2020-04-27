package com.seat.robot.pollution.collector.robot.model;

import com.google.api.gbase.client.Location;

public class PollutionInfo {
    private final Location location;
    private final int level;

    public PollutionInfo(Location location, int level) {
        this.location = location;
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Lectura{" +
                "location=" + location +
                ", level=" + level +
                '}';
    }
}
