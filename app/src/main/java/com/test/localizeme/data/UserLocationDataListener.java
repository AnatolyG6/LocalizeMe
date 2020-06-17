package com.test.localizeme.data;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

public class UserLocationDataListener implements LocationListener {
    private MutableLiveData<UserLocationData> mutableSpeedLiveData = new MutableLiveData<>();
    public LiveData<UserLocationData> speedLiveData = mutableSpeedLiveData;
    @Inject
    UserLocationDataListener(){}

    @Override
    public void onLocationChanged(Location location) {
        float currentUserSpeed = location.getSpeed();
        double userXPosition = location.getLatitude();
        double userYPosition = location.getLongitude();
        mutableSpeedLiveData.setValue(
                new UserLocationData(currentUserSpeed, userXPosition, userYPosition)
        );
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // no action
    }

    @Override
    public void onProviderEnabled(String provider) {
        // no action
    }

    @Override
    public void onProviderDisabled(String provider) {
        //no action
    }
}
