package com.example.ezequiel.espressodagger.di;

import android.content.Context;

import com.example.ezequiel.espressodagger.rest.MockWeatherApiClient;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;

import dagger.Module;
import dagger.Provides;

@Module
public class TestAppModule {

    private final Context context;

    public TestAppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context provideAppContext() {
        return context;
    }

    @Provides public WeatherApiClient provideWeatherApiClient() {
        return new MockWeatherApiClient();
    }
}