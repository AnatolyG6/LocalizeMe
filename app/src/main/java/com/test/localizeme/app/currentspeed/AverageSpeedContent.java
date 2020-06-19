package com.test.localizeme.app.currentspeed;

public class AverageSpeedContent {
    private int averageSpeed;
    private int birdFlySpeed;

    public AverageSpeedContent(int averageSpeed, int birdFlySpeed) {
        this.averageSpeed = averageSpeed;
        this.birdFlySpeed = birdFlySpeed;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public int getBirdFlySpeed() {
        return birdFlySpeed;
    }
}
