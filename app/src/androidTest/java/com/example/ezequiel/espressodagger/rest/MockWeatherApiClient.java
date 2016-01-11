package com.example.ezequiel.espressodagger.rest;

import com.example.ezequiel.espressodagger.data.TestData;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;
import com.example.ezequiel.espressodagger.data.WeatherData;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MockWeatherApiClient implements WeatherApiClient {

    @Override public Observable<WeatherData> getWeatherForCity(String cityName) {
        WeatherData weatherData = new Gson().fromJson(TestData.MUNICH_WEATHER_DATA_JSON, WeatherData.class);
        return Observable.just(weatherData).delay(1, TimeUnit.SECONDS);
    }
}