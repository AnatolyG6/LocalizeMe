package com.test.localizeme.app.currentspeed;

import com.test.localizeme.data.UserLocationData;

import javax.inject.Inject;

public class SpeedMapper {

    @Inject
    SpeedMapper(){}

    public int mapUserSpeed(UserLocationData userLocationData) {
        return (int) Math.round(userLocationData.getUserSpeed() * 3.6);
    }
}
