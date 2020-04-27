package com.seat.robot.pollution.collector.robot.model;

import com.google.api.gbase.client.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;

public class Report {
    private final long instant;
    private final Location location;
    String level;
    String source;

    public Report(Location location, String level, String source) {
        this.instant = Instant.now().getEpochSecond();
        this.location = location;
        this.level = level;
        this.source = source;
    }

    public long getInstant() {
        return instant;
    }

    public Location getLocation() {
        return location;
    }

    public String getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
