package com.example.ejemplomvvm;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AleatorioUI extends AppCompatActivity {
    Button btn;
    TextView label;

    LiveData<Integer> observableSoloTocar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.cambia);
        label=findViewById(R.id.idLabel);

        AleatorioViewModel vm=new ViewModelProvider(this).get(AleatorioViewModel.class);
        observableSoloTocar =vm.getDatoAleatorio();
        observableSoloTocar.observe(this, dato->{
            label.setText(dato+"");
        });

        btn.setOnClickListener(v->{
            label.setText("gif girando");
            vm.nuevoAleatorio();
        });
    }
}