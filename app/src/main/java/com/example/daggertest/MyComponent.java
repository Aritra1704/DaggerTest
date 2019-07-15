package com.example.daggertest;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Singleton
@Component(modules = SharedPrefModule.class)
public interface MyComponent {
    void inject(MainActivity activity);
}
