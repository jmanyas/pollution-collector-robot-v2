package com.seat.robot.pollution.collector.robot.service.robot;

import com.google.maps.model.LatLng;
import com.seat.robot.pollution.collector.robot.service.distance.DistanceManagerLocal;
import com.seat.robot.pollution.collector.robot.service.distance.DistanceServiceFactory;
import com.seat.robot.pollution.collector.robot.service.reporting.ReportingManager;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RobotManagerBeanTest {

    @Mock
    DistanceServiceFactory distanceServiceFactory;
    @Mock
    DistanceManagerLocal distanceManagerLocal;

    @Mock
    ReportingManager reportingManager;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(distanceServiceFactory.getDistanceManager()).thenReturn(distanceManagerLocal);
        when(distanceManagerLocal.calculateDistance(any(), any())).thenReturn(10.0);
        when(distanceManagerLocal.getPathCoordinates(any())).thenReturn(createLatLngList());
    }

    private List<LatLng> createLatLngList() {
        LatLng latLng = new LatLng(10f,10f);
        LatLng latLng2 = new LatLng(20f,20f);
        return Arrays.asList(latLng, latLng2);
    }

    @Test
    public void testStart() throws InterruptedException {
        RobotManagerBean robotManagerBean = new RobotManagerBean(distanceServiceFactory, reportingManager);
        robotManagerBean.setSpeed(10);
        robotManagerBean.setPath("ksl{F{ksKTtB");
        robotManagerBean.startRobot();
        verify(distanceManagerLocal, times(1))
                .calculateDistance(any(), any());
    }



}