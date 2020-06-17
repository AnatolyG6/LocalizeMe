package com.test.localizeme.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final Context context;

    public MainActivityModule (Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }
}