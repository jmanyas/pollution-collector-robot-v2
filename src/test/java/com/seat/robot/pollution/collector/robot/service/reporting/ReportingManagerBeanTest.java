package com.seat.robot.pollution.collector.robot.service.reporting;

import com.google.api.gbase.client.Location;
import com.seat.robot.pollution.collector.robot.model.PollutionInfo;
import com.seat.robot.pollution.collector.robot.model.PollutionLevel;
import com.seat.robot.pollution.collector.robot.model.Report;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReportingManagerBeanTest {

    ReportingManagerBean reportingManagerBean = new ReportingManagerBean();

    Location location = new Location("home",10f,10f);
    PollutionInfo pollutionInfo = new PollutionInfo(location, 10);

    @Test
    public void testAddLectura() {
        reportingManagerBean.initializeLecturas();
        assertTrue(reportingManagerBean.getLecturas().isEmpty());
        reportingManagerBean.addLectura(pollutionInfo);
        assertEquals(reportingManagerBean.getLecturas().size(), 1);
    }

    @Test
    public void testClear() {
        reportingManagerBean.initializeLecturas();
        assertTrue(reportingManagerBean.getLecturas().isEmpty());
        reportingManagerBean.addLectura(pollutionInfo);
        reportingManagerBean.initializeLecturas();
        assertTrue(reportingManagerBean.getLecturas().isEmpty());
    }

    @Test
    public void testGenerateReport() {
        PollutionInfo pollutionInfo2 = new PollutionInfo(location, 30);
        PollutionInfo pollutionInfo3 = new PollutionInfo(location, 300);
        reportingManagerBean.initializeLecturas();
        reportingManagerBean.addLectura(pollutionInfo);
        reportingManagerBean.addLectura(pollutionInfo2);
        Report testReport = reportingManagerBean.generateReport("test");
        assertEquals(PollutionLevel.Good.toString(), testReport.getLevel());
        reportingManagerBean.addLectura(pollutionInfo3);
        Report testReport2 = reportingManagerBean.generateReport("test");
        assertEquals(PollutionLevel.USG.toString(), testReport2.getLevel());
    }

}