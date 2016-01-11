package com.example.ezequiel.espressodagger.di;

import com.example.ezequiel.espressodagger.tests.MainActivityTest;

import dagger.Component;

@AppScope
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {

    void inject(MainActivityTest test);
}
