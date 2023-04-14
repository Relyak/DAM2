package com.example.piedrapapeltijera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Juego extends AppCompatActivity implements View.OnClickListener {
    ImageButton piedra,papel,tijera,reset;
    TextView puntos, resurtado;
    int eleccionJugador;
    int eleccionPc;
    int puntuacionJugador=0;
    int puntuacionPc=0;
    static final int PIEDRA=1;
    static final int PAPEL=2;
    static final int TIJERA=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        piedra=findViewById(R.id.piedra);
        piedra.setOnClickListener(this);
        papel=findViewById(R.id.papel);
        papel.setOnClickListener(this);
        tijera=findViewById(R.id.tijera);
        tijera.setOnClickListener(this);
        puntos=findViewById(R.id.puntos);
        resurtado =findViewById(R.id.eleccionMaquina);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        juego(view.getId());
    }
    public void juego(int opcion){
        //1=piedra 2=papel 3=tijera
        eleccionPc=(1+(int)(Math.random()*3));
        switch (opcion){
            case R.id.piedra:
                if(eleccionPc==PIEDRA){
                    resurtado.setText("PC eligió PIEDRA, EMPATE");
                }else if(eleccionPc==PAPEL){
                    resurtado.setText("PC eligió PAPEL, PERDISTE");
                    puntuacionPc++;
                }else if(eleccionPc==TIJERA){
                    resurtado.setText("PC eligió TIJERA, GANASTE");
                    puntuacionJugador++;
                }
                break;
            case R.id.papel:
                if(eleccionPc==PIEDRA){
                    resurtado.setText("PC eligió PIEDRA, GANASTE");
                    puntuacionJugador++;
                }else if(eleccionPc==PAPEL){
                    resurtado.setText("PC eligió PAPEL, EMPATE");
                }else if(eleccionPc==TIJERA){
                    resurtado.setText("PC eligió TIJERA, PERDISTE");
                    puntuacionPc++;
                }
                break;
            case R.id.tijera:
                if(eleccionPc==PIEDRA){
                    resurtado.setText("PC eligió PIEDRA, PERDISTE");
                    puntuacionPc++;
                }else if(eleccionPc==PAPEL){
                    resurtado.setText("PC eligió PAPEL, GANASTE");
                    puntuacionJugador++;
                }else if(eleccionPc==TIJERA){
                    resurtado.setText("PC eligió tijera, EMPATE");
                }
                break;
            case R.id.reset:
                resurtado.setText("VAS PERDIENDO Y QUIERES VOLVER A EMPEZAR\nLos puntos se han restablecido\n (.________________________________________.)");
                puntuacionJugador=0;
                puntuacionPc=0;
        }
        puntos.setText("\nPuntos jugador: "+puntuacionJugador+"\nPuntos PC: "+puntuacionPc);
    }

}