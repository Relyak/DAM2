package com.example.contador;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contador extends AppCompatActivity implements View.OnClickListener {

    int contador;
    Button suma,resta,reset;
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contador);
        contador=0;
        suma = findViewById(R.id.suma);
        suma.setOnClickListener(this);
        resta = findViewById(R.id.resta);
        resta.setOnClickListener(this);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
        texto = findViewById(R.id.texto);


    }
    //aqui metodos


    @Override
    public void onClick(View view) {
        nintendo(view.getId());
    }
    public void nintendo(int opcion){
        switch (opcion){
            case R.id.suma:
                contador++;
                break;
            case R.id.resta:
                if(0>=contador){
                    contador=0;
                }else{
                    contador--;
                }
                break;
            case R.id.reset:
                contador=0;
                break;
        }
        texto.setText(String.valueOf(contador));
    }
}