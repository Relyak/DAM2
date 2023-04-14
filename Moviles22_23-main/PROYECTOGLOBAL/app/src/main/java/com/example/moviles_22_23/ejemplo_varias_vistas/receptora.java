package com.example.moviles_22_23.ejemplo_varias_vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moviles_22_23.R;

public class receptora extends AppCompatActivity {

    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptora);

        mensaje = findViewById(R.id.ut02idRecpetora);
        Intent i = getIntent();
        mensaje.setText(i.getStringExtra(lanzadora.CLAVE_INFO));
    }
}