package com.seat.robot.pollution.collector.robot.service.distance;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import java.util.List;

public abstract class DistanceManager {

    public List<LatLng> getPathCoordinates(String path) {
        return PolylineEncoding.decode(path);
    }

    public abstract double calculateDistance(LatLng latLng, LatLng latLng1);
}
