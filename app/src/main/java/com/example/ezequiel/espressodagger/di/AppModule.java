package com.example.ezequiel.espressodagger.di;

import android.content.Context;

import com.example.ezequiel.espressodagger.BuildConfig;
import com.example.ezequiel.espressodagger.R;
import com.example.ezequiel.espressodagger.rest.ApiKeyRequestInterceptor;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context){
        this.context = context.getApplicationContext();
    }

    @Provides @AppScope
    public Context provideAppContext(){
        return context;
    }

    @Provides public WeatherApiClient provideWeatherApiClient(){
        return new RestAdapter.Builder()
                .setEndpoint(WeatherApiClient.ENDPOINT)
                .setRequestInterceptor(apiKeyRequestInterceptor())
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build()
                .create(WeatherApiClient.class);
    }

    private RequestInterceptor apiKeyRequestInterceptor(){
        return new ApiKeyRequestInterceptor(context.getString(R.string.open_weather_api_key));
    }
}
