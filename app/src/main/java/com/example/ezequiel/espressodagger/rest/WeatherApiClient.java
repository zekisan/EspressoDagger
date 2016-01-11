package com.example.ezequiel.espressodagger.rest;

import com.example.ezequiel.espressodagger.data.WeatherData;

import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherApiClient {

    Endpoint ENDPOINT = Endpoints.newFixedEndpoint("http://api.openweathermap.org/data/2.5");

    @GET("/weather")
    Observable<WeatherData> getWeatherForCity(@Query("q") String cityName);

}
