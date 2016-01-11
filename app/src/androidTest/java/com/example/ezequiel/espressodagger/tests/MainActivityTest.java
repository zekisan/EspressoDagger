package com.example.ezequiel.espressodagger.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.example.ezequiel.espressodagger.TestWeatherApp;
import com.example.ezequiel.espressodagger.data.WeatherData;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;
import com.example.ezequiel.espressodagger.ui.activities.MainActivity;
import com.example.ezequiel.espressodagger.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String CITY_NAME = "München";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Inject
    WeatherApiClient weatherApiClient;

    @Before
    public void setUp() {
        ((TestWeatherApp) activityTestRule.getActivity().getApplication()).appComponent().inject(this);
    }

    @Test
    public void correctWeatherDataDisplayed() {
        WeatherData weatherData = weatherApiClient.getWeatherForCity(CITY_NAME).toBlocking().first();

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(replaceText(CITY_NAME));
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withId(R.id.city_name)).check(matches(withText(weatherData.getCityName())));
        onView(withId(R.id.weather_date)).check(matches(withText(weatherData.getWeatherDate())));
        onView(withId(R.id.weather_state)).check(matches(withText(weatherData.getWeatherState())));
        onView(withId(R.id.weather_description)).check(matches(withText(weatherData.getWeatherDescription())));
        onView(withId(R.id.temperature)).check(matches(withText(weatherData.getTemperatureCelsius())));
        onView(withId(R.id.humidity)).check(matches(withText(weatherData.getHumidity())));
    }
}