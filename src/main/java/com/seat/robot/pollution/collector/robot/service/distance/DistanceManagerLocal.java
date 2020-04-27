package com.seat.robot.pollution.collector.robot.service.distance;

import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DistanceManagerLocal extends DistanceManager {
    @Override
    public double calculateDistance(LatLng latLng, LatLng latLng1) {
        Random random = new Random();
        return random.nextInt(75);
    }
}
