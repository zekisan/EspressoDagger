package com.example.ezequiel.espressodagger.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherData {

    public static final String DATE_FORMAT = "EEEE, d MMM";

    private static final int KELVIN_ZERO = 273;

    private static final String FORMAT_TEMPERATURE_CELSIUS = "%d°";
    private static final String FORMAT_HUMIDITY = "%d%%";

    private String name;
    private Weather[] weather;
    private Main main;

    public String getCityName() {
        return name;
    }

    public String getWeatherDate() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());
    }

    public String getWeatherState() {
        return weather().main;
    }

    public String getWeatherDescription() {
        return weather().description;
    }

    public String getTemperatureCelsius() {
        return String.format(FORMAT_TEMPERATURE_CELSIUS, (int) main.temp - KELVIN_ZERO);
    }

    public String getHumidity() {
        return String.format(FORMAT_HUMIDITY, main.humidity);
    }

    private Weather weather() {
        return weather[0];
    }

    private static class Weather {
        private String main;
        private String description;
    }

    private static class Main {
        private float temp;
        private int humidity;
    }

}
