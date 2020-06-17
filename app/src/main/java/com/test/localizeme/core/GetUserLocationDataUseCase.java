package com.test.localizeme.core;

import androidx.lifecycle.LiveData;

import com.test.localizeme.data.UserLocationData;

import javax.inject.Inject;

public class GetUserLocationDataUseCase {
    private LocationRepository locationRepository;
    public LiveData<UserLocationData> userLocationLiveData;

    @Inject
    GetUserLocationDataUseCase(
            LocationRepository locationRepository
    ) {
        this.locationRepository = locationRepository;
        this.userLocationLiveData = locationRepository.getUserLocationLiveData();
    }

    // Use a simple listener as a parameter is more appropriate for clean architecture
    public void initLocationTracing() {
        locationRepository.setUserLocationListener();
    }
}
