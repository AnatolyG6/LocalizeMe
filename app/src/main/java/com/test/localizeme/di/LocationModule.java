package com.test.localizeme.di;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;

import androidx.annotation.Nullable;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {

    @Provides
    @Singleton
    public LocationManager provideLocationManager(Context context) {
        return (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    @Named("locationProvider")
    public String provideLocationProvider(LocationManager locationManager) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            return provider;
        } else {
            // A little hack that can be avoided if don't inject locationProvider
            return "";
        }
    }
}
