package com.example.ezequiel.espressodagger.ui.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import com.example.ezequiel.espressodagger.R;
import com.example.ezequiel.espressodagger.WeatherApp;
import com.example.ezequiel.espressodagger.databinding.ActivityMainBinding;
import com.example.ezequiel.espressodagger.rest.WeatherApiClient;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String LOGTAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    private MenuItem searchMenuItem;

    @Inject WeatherApiClient weatherApiClient;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApp) getApplication()).appComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        tintSearchMenuItem();
        initSearchView();
        return true;
    }

    private void tintSearchMenuItem() {
        int color = ContextCompat.getColor(this, android.R.color.white);
        searchMenuItem.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    private void initSearchView() {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            MenuItemCompat.collapseActionView(searchMenuItem);
            loadWeatherData(query);
        }
        return true;
    }

    private void loadWeatherData(String cityName) {
        binding.progress.setVisibility(View.VISIBLE);
        subscription = weatherApiClient.getWeatherForCity(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            binding.progress.setVisibility(View.INVISIBLE);
                            binding.weatherLayout.setVisibility(View.VISIBLE);
                            binding.setWeatherData(weatherData);
                        },
                        throwable -> {
                            binding.progress.setVisibility(View.INVISIBLE);
                            Log.e(LOGTAG, throwable.getLocalizedMessage(), throwable);
                        }
                );
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    protected void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}
