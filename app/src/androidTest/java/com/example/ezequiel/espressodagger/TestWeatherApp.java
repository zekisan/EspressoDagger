package com.example.ezequiel.espressodagger;

import com.example.ezequiel.espressodagger.di.DaggerTestAppComponent;
import com.example.ezequiel.espressodagger.di.TestAppComponent;
import com.example.ezequiel.espressodagger.di.TestAppModule;


public class TestWeatherApp extends WeatherApp {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    @Override
    public TestAppComponent appComponent() {
        return testAppComponent;
    }
}
