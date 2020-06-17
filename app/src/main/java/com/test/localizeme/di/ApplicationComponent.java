package com.test.localizeme.di;

import android.app.Application;
import android.content.Context;

import com.test.localizeme.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = {RepositoryModule.class})
interface ApplicationComponent {
    Context context();

    void inject(MainActivity main);
}