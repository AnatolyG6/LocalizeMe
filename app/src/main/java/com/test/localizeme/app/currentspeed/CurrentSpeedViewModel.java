package com.test.localizeme.app.currentspeed;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.test.localizeme.core.GetUserLocationDataUseCase;
import com.test.localizeme.data.UserLocationData;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Lazy;

public class CurrentSpeedViewModel {
    @Inject
    protected Lazy<GetUserLocationDataUseCase> useCase;
    private SpeedMapper speedMapper;
    private MutableLiveData<MainActivityContent> privateMainActivityContent =
            new MutableLiveData<>();
    public LiveData<MainActivityContent> mainActivityContent = privateMainActivityContent;
    public ArrayList<UserLocationData> userLocations = new ArrayList<>();

    @Inject
    CurrentSpeedViewModel(
            SpeedMapper speedMapper
    ) {
        this.speedMapper = speedMapper;
    }

    public void initView(LifecycleOwner lifecycleOwner) {
        useCase.get().userLocationLiveData.observe(
                lifecycleOwner,
                userLocationData -> {
                    userLocations.add(userLocationData);

                    MainActivityContent mainContent = new MainActivityContent(
                        speedMapper.mapUserSpeed(userLocationData)
                    );
                    privateMainActivityContent.setValue(mainContent);
                }
        );

        useCase.get().initLocationTracing();
    }
}
