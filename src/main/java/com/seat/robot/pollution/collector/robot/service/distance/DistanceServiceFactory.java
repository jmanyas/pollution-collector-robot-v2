package com.seat.robot.pollution.collector.robot.service.distance;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DistanceServiceFactory {

    private static final Logger log = LoggerFactory.getLogger(DistanceServiceFactory.class);

    private DistanceManager distanceManager;

    private final DistanceManagerLocal distanceManagerLocal;
    private final DistanceManagerGoogle distanceManagerGoogle;
    private final String distanceService;

    public DistanceServiceFactory(DistanceManagerLocal distanceManagerLocal, DistanceManagerGoogle distanceManagerGoogle, @Value("${distanceService}") String distanceService) {
        this.distanceManagerLocal = distanceManagerLocal;
        this.distanceManagerGoogle = distanceManagerGoogle;
        this.distanceService = distanceService;

        log.info("Using Distance Service: " + distanceService);

        if ( this.distanceService.compareToIgnoreCase("Local") == 0 ) {
            this.distanceManager = this.distanceManagerLocal;
        } else {
            this.distanceManager = this.distanceManagerGoogle;
        }
    }

    public DistanceManager getDistanceManager() {
        return this.distanceManager;
    }

}
