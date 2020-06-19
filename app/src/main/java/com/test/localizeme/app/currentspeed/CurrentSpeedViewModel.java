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
    private MutableLiveData<AverageSpeedContent> privateAverageSpeedContent =
            new MutableLiveData<>();
    public LiveData<AverageSpeedContent> averageSpeedContent = privateAverageSpeedContent;
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
                    if (checkIfUserDrive()) {
                        MainActivityContent mainContent = new MainActivityContent(
                                speedMapper.mapUserSpeed(userLocationData)
                        );
                        privateMainActivityContent.setValue(mainContent);
                    } else {
                        AverageSpeedContent averageContent
                                = speedMapper.mapAverageSpeed(userLocations);
                        privateAverageSpeedContent.setValue(averageContent);
                        userLocations.clear();
                    }
                }
        );

        useCase.get().initLocationTracing();
    }

    private boolean checkIfUserDrive() {
        int checkLastSpeedStatementNumber = 2;
        int checkStartSpeedStatementNumber = 10;
        float minimalDrivingSpeedLimit = 4f;
        float lastStatementAverageSpeed = 0;
        float firstStatementAverageSpeed = 0;

        int startCheckIndex = userLocations.size() - 1 - checkLastSpeedStatementNumber;
        int endCheckIndex = userLocations.size() - 1;

        if (userLocations.size() < checkStartSpeedStatementNumber) {
            return true;
        }

        for (UserLocationData userData : userLocations.subList(0, checkStartSpeedStatementNumber)) {
            firstStatementAverageSpeed += userData.getUserSpeed();
        }
        firstStatementAverageSpeed /= checkStartSpeedStatementNumber;

        if (firstStatementAverageSpeed < minimalDrivingSpeedLimit) {
            userLocations.clear();
            return true;
        }

        for (UserLocationData userData : userLocations.subList(startCheckIndex, endCheckIndex)) {
            lastStatementAverageSpeed += userData.getUserSpeed();
        }
        lastStatementAverageSpeed /= checkLastSpeedStatementNumber;

        return !(lastStatementAverageSpeed < minimalDrivingSpeedLimit);
    }

    public void clearData() {
        userLocations.clear();
    }
}
