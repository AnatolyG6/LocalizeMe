package com.test.localizeme.di;

import com.test.localizeme.core.LocationRepository;
import com.test.localizeme.data.LocationRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
abstract class RepositoryModule {

    @Binds
    abstract LocationRepository bindLocalRepository(LocationRepositoryImpl locationRepository);
}
