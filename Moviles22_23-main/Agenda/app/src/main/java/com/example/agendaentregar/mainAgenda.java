package com.example.agendaentregar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mainAgenda extends AppCompatActivity implements View.OnClickListener {
    private List<tablaPersona> personas;
    private RecyclerView rv1;
    appDataBase db;//ni idea brother
    Button anadir;
    recyclerAdapter adapter;
    private EditText et1;
    private ImageView iv1;
    private PersonaDAO personaDAO;
    private recyclerAdapter.RecyclerViewClickListener listener;
    private EditText et2;
    ActivityResultLauncher recibidorLauncher;
    private String nom,tel;
    ActivityResultLauncher<Intent>imgResult;
    private Uri uriCapturada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_agenda);
        rv1=findViewById(R.id.rv1);
        personas=new ArrayList<>();
        getSupportActionBar().hide();//oculta barra de arriba
        createORM();
        setAdapter();
        refrescarRV();

        anadir=findViewById(R.id.anadir); anadir.setOnClickListener(this);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et1.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        et2.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        iv1=findViewById(R.id.imagenMain);
        iv1.setOnClickListener(this);

        imgResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();
                        uriCapturada = data.getData();
                        getContentResolver().takePersistableUriPermission(uriCapturada, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        String s = uriCapturada.toString();
                        iv1.setImageURI(Uri.parse(s));
                    }
                }
        );
        launcher();
    }
    public void launcher(){

        recibidorLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    refrescarRV();


                });
    }
    public void createORM () {
        //Se crea la base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                appDataBase.class, "tablaPersona").allowMainThreadQueries().build();
        personaDAO = db.personaDAO();//extrae datos de la bd para darle al dao?

        //extr4e el contenido de la bd
        personas= personaDAO.getAll();//le estoy metiendo
        //el objeto, la lista, está extrayendo todas las tuplas
        //y se la meto al objeto uwu

        //Recorre la base e anade al ArrayList utilizado
        //mirar como funciona for each:
        //primer valor tabla, segundo valor objeto bd





    }
    private void setAdapter() {
        setOnClickListener();
        //Toast.makeText(getApplicationContext(),personas.get(0).getTel(), Toast.LENGTH_SHORT).show();
        adapter=new recyclerAdapter(personas,listener);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        rv1.setLayoutManager(layoutManager);
        rv1.setItemAnimator(new DefaultItemAnimator());
        rv1.setAdapter(adapter);


    }

    private void setOnClickListener() {
        listener= new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent= new Intent(getApplicationContext(),perfilPersona.class);
                intent.putExtra("nombre",personas.get(position).getNombre());
                intent.putExtra("tel",personas.get(position).getTel());
                intent.putExtra("img",personas.get(position).getImagen());
                recibidorLauncher.launch(intent);




            }
        };
    }


    @Override
    public void onClick(View view) {
        nintendo(view.getId());
    }
    public void nintendo(int aaaa){
        switch(aaaa){
            case R.id.anadir:
                nom=et1.getText().toString();
                tel=et2.getText().toString();

                if(nom.equals("")){
                    et1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }else{
                    et1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                }
                if(tel.equals("")){
                    et2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }else{
                    et2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                }
                if(
                        (!nom.equals(""))&&
                                (!tel.equals(""))
                ){
                    tablaPersona datoss=new tablaPersona();
                    datoss.nombre=nom;
                    datoss.telefono=tel;
                    datoss.imagen=uriCapturada.toString();
                    personaDAO.insertAll(datoss);

                    et1.setText("");
                    et2.setText("");
                    Toast.makeText(this,"Persona agregada",Toast.LENGTH_SHORT).show();
                    refrescarRV();
                }else{
                    Toast.makeText(this,"Datos vacios",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imagenMain:
                metelImagen();

                break;

        }
    }
    public void refrescarRV(){
        personas= personaDAO.getAll();
        setAdapter();
    }
    public void metelImagen(){
        Intent i =new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uriCapturada);
        imgResult.launch(i);
    }



}