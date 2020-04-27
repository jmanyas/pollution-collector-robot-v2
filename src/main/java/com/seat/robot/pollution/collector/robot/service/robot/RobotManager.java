package com.seat.robot.pollution.collector.robot.service.robot;

public interface RobotManager {
    void startRobot() throws InterruptedException;
    int setSpeed(int speed);
    void setPath(String path);
    void stopRobot();
}
