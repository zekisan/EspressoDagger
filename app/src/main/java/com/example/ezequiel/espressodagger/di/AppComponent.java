package com.example.ezequiel.espressodagger.di;

import android.content.Context;

import com.example.ezequiel.espressodagger.ui.activities.MainActivity;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;

import dagger.Component;

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainActivity activity);

    @AppScope Context appContext();

    WeatherApiClient weatherApiClient();
}
