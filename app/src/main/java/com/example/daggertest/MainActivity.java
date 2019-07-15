package com.example.daggertest;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.daggertest.components.AppComponent;
import com.example.daggertest.components.DaggerAppComponent;
import com.example.daggertest.modules.CalendarModule;
import com.example.daggertest.modules.SharedPrefModule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inUsername, inNumber;
    Button btnSave, btnGet;
    private AppComponent component;
    @Inject
    SharedPreferences preferences;
    @Inject
    CalendarModule calendarModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initViews();
        component = DaggerAppComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        component.injectPreference(this);
        component.injectCalendar(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGet:
                inUsername.setText(preferences.getString("Username", "default"));
                inNumber.setText(preferences.getString("number", "12345"));
                Toast.makeText(this, calendarModule.getCurrentDateinPattern(CalendarModule.DATE_FORMAT_WITH_COMMA), Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnSave:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Username", inUsername.getText().toString().trim());
                editor.putString("number", inNumber.getText().toString().trim());
                editor.apply();
                break;
        }
    }

    private void initViews() {
        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        inUsername = findViewById(R.id.inUsername);
        inNumber = findViewById(R.id.inNumber);
        btnSave.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }
}
