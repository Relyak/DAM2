package com.example.moviles_22_23.ejemplo_varias_vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviles_22_23.R;

public class lanzadora extends AppCompatActivity {

    public static String CLAVE_INFO = "PRIMERACOMUNICACION";

    Button lanzador;
    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanzadora);

        lanzador = findViewById(R.id.ut03lanzador);
        nombre = findViewById(R.id.ut03nombre);

        lanzador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzar();
            }
        });
    }

    private void lanzar(){
        Intent intento =  new Intent(this, receptora.class);
        intento.putExtra(CLAVE_INFO, nombre.getText().toString());
        startActivity(intento);
    }
}