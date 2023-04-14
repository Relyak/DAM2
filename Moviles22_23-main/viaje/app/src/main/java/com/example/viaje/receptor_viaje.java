package com.example.viaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class receptor_viaje extends AppCompatActivity {
    TextView datos;
    cViaje objetoDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receptor_viaje);

        datos = findViewById(R.id.datos);
        Intent i = getIntent();
        //datos.setText(i.getStringExtra(lanzador_viaje.IDENTIFICADOR_RECEPTOR));
        objetoDatos=(cViaje)getIntent().getSerializableExtra(lanzador_viaje.IDENTIFICADOR_RECEPTOR);
        datos.setText(objetoDatos.toString());
    }
}