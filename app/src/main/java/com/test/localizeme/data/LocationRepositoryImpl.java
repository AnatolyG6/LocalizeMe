package com.test.localizeme.data;

import android.annotation.SuppressLint;
import android.location.LocationManager;
import androidx.lifecycle.LiveData;
import com.test.localizeme.core.LocationRepository;
import javax.inject.Inject;
import javax.inject.Named;

public class LocationRepositoryImpl implements LocationRepository {
    private UserLocationDataListener userLocationDataListener;
    private LocationManager locationManager;
    private String locationProvider;

    @Inject
    LocationRepositoryImpl(
            UserLocationDataListener userLocationDataListener,
            LocationManager locationManager,
            @Named("locationProvider") String locationProvider
    ) {
        this.userLocationDataListener = userLocationDataListener;
        this.locationManager = locationManager;
        this.locationProvider = locationProvider;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void setUserLocationListener() {
        locationManager.requestLocationUpdates(
                locationProvider,
                500,
                5,
                userLocationDataListener
        );
    }

    @Override
    public LiveData<UserLocationData> getUserLocationLiveData() {
        return userLocationDataListener.speedLiveData;
    }
}
