package com.example.daggertest.components;

import com.example.daggertest.MainActivity;
import com.example.daggertest.modules.CalendarModule;
import com.example.daggertest.modules.SharedPrefModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class, CalendarModule.class})
public interface AppComponent {
    void injectPreference(MainActivity activity);
    void injectCalendar(MainActivity activity);
}
