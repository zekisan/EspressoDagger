package com.example.ezequiel.espressodagger;


import android.app.Application;

import com.example.ezequiel.espressodagger.di.AppComponent;
import com.example.ezequiel.espressodagger.di.AppModule;
import com.example.ezequiel.espressodagger.di.DaggerAppComponent;

public class WeatherApp extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent(){
        return appComponent;
    }
}
