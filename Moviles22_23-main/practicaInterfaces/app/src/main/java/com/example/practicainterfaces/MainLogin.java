package com.example.practicainterfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_login);
            getSupportActionBar().hide();
    }
}