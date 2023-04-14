package com.example.ejemplomvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final double MAX_VALUE =100;
    Button btn;
    TextView label;

    MutableLiveData<Integer> datoObservable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.cambia);
        label=findViewById(R.id.idLabel);
        datoObservable= new MutableLiveData<Integer>(0);
        /*primer parametro actividad segundo instanciación que puede ser cambiado por lambda
        datoObservable.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
            }
        });*/

        datoObservable.observe(this,dato->{
            label.setText(dato+"");
        });
        btn.setOnClickListener(v->{
            //se puede usar con setValue en este mismo thread, postValue lo actualiza para todos los threads
            datoObservable.setValue(new Integer((int)(Math.random()*MAX_VALUE)));
        });
    }
}