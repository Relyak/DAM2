package com.example.logininterfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import pl.droidsonroids.gif.GifImageView;

public class otraInterfaz extends AppCompatActivity {
    MediaPlayer player;
    GifImageView gif;
    Button para;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otra_interfaz);
        player=MediaPlayer.create(this,R.raw.cancion_interfaces);
        gif=findViewById(R.id.gifInterfaces);
        para=findViewById(R.id.boton_para);
        para.setOnClickListener(v -> para());
        empieza();
    }
    private void empieza(){
        player.start();
        try {
            Thread.sleep(3200);
            gif.setVisibility(View.VISIBLE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void para() {gif.setVisibility(View.INVISIBLE);
        player.stop();
    }


}
