package com.test.localizeme.core;

import androidx.lifecycle.LiveData;

import com.test.localizeme.data.UserLocationData;

public interface LocationRepository {
    public void setUserLocationListener();
    public LiveData<UserLocationData> getUserLocationLiveData();
}
