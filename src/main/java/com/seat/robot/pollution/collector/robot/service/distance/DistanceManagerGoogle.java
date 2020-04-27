package com.seat.robot.pollution.collector.robot.service.distance;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class DistanceManagerGoogle extends DistanceManager {

    GeoApiContext geoApiContext = new GeoApiContext.Builder()
            .apiKey("AIzaSyDL8Ku0JrxX39zaNJCo6VrVIfjU5SJEuYg").build();

    @Override
    public double calculateDistance(LatLng origin, LatLng destination) {

        double result = 0;

        try {
            DirectionsApiRequest path = DirectionsApi.newRequest(geoApiContext)
                    .origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING);

            DirectionsResult await = path.await();
            DirectionsRoute[] routes = await.routes;

            result += Arrays.stream(routes).map(route -> route.legs).flatMap(Arrays::stream).mapToLong(leg -> leg.distance.inMeters).sum();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
