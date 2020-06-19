package com.test.localizeme.app.currentspeed;

import com.test.localizeme.data.UserLocationData;

import java.util.ArrayList;

import javax.inject.Inject;

public class SpeedMapper {

    @Inject
    SpeedMapper() {
    }

    public int mapUserSpeed(UserLocationData userLocationData) {
        return (int) Math.round(userLocationData.getUserSpeed() * 3.6);
    }

    public AverageSpeedContent mapAverageSpeed(ArrayList<UserLocationData> userLocations) {
        float averageSpeed = 0;
        double birdFlySpeed = 0;

        for (UserLocationData userData : userLocations) {
            averageSpeed += userData.getUserSpeed();
        }
        averageSpeed /= userLocations.size();

        int lastIndex = userLocations.size() - 1;
        double startPositionX = userLocations.get(0).getUserXPosition();
        double startPositionY = userLocations.get(0).getUserYPosition();
        double endPositionX = userLocations.get(lastIndex).getUserXPosition();
        double endPositionY = userLocations.get(lastIndex).getUserYPosition();

        double distanceX = (endPositionY - startPositionY) *
                Math.cos((startPositionX + endPositionX) / (180 * 2));
        double distanceY = endPositionX - startPositionX;

        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) * 1852 * 60;

        float secondsNumberBetweenStatements = 1;

        birdFlySpeed = distance / (userLocations.size() * secondsNumberBetweenStatements);

        averageSpeed *= 3.6;
        birdFlySpeed *= 3.6;

        return new AverageSpeedContent(
                (int)averageSpeed,
                (int)birdFlySpeed
        );
    }
}
