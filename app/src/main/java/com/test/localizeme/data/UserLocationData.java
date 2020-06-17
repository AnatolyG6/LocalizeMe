package com.test.localizeme.data;

public class UserLocationData {
    private float userSpeed;
    private double userXPosition;
    private double userYPosition;

    UserLocationData(float userSpeed, double userXPosition, double userYPosition) {
        this.userSpeed = userSpeed;
        this.userXPosition = userXPosition;
        this.userYPosition = userYPosition;
    }

    public float getUserSpeed() {
        return userSpeed;
    }

    public double getUserXPosition() {
        return userXPosition;
    }

    public double getUserYPosition() {
        return userYPosition;
    }
}
