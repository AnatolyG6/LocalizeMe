package com.test.localizeme.di;

import android.content.Context;

import com.test.localizeme.app.currentspeed.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MainActivityModule.class,
        RepositoryModule.class,
        LocationModule.class,
})
public interface ApplicationComponent {
    Context context();

    void inject(MainActivity main);
}