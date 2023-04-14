package com.example.ejemplo_bd;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class EjecutarBDD extends AppCompatActivity {
    Button anadir;
    Button recargar;
    EditText nombre,numero;
    TextView mostrar;
    AppDatabase db;
    PokemonDAO pokedexDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_pokemon);

        anadir=findViewById(R.id.anadirPoke);
        recargar=findViewById(R.id.recarga);
        nombre=findViewById(R.id.nombrePoke);
        numero=findViewById(R.id.numPoke);
        mostrar=findViewById(R.id.mostrarBD);



        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "pokedex").allowMainThreadQueries().build();
        pokedexDao=db.pokedexDAO();
        recargar.setOnClickListener( v->{
            List<Pokemon> pokemon=pokedexDao.getAll();
            String sDatos="";
            for (Pokemon a: pokemon){
                sDatos+=a.nombre +" num dex: "+a.num+"\n";
            }
            mostrar.setText(sDatos);
        });
        anadir.setOnClickListener(v->{
            Pokemon a= new Pokemon();
            a.nombre=nombre.getText().toString();
            a.num=numero.getText().toString();
            pokedexDao.insertAll(a);
        });



    }
}