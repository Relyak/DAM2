package com.example.agendaentregar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;
import java.util.List;

public class perfilPersona extends AppCompatActivity implements View.OnClickListener {
    appDataBase db;
    public static int PARAFILTRAR = 10;
    private PersonaDAO personaDAO;
    private ArrayList<tablaPersona> personas;
    List<tablaPersona> personaBD;
    TextView tvNombre, tvTel;
    EditText etNombre, etTel;
    Button boton, borrar, modificar;
    private Uri uriCapturada;
    ImageView imagen;
    Intent intent;
    ActivityResultLauncher<String> requestPermissionLauncher;

    ActivityResultLauncher<Intent> imgResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_persona);
        createORM();
        personas = new ArrayList<>();
        getSupportActionBar().hide();//oculta barra de arriba
        tvNombre = findViewById(R.id.tvNom);
        tvTel = findViewById(R.id.tvTel);
        String nombre = "Nombre no encontrado";
        String tel = "Telefono no encontrado";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nombre = extras.getString("nombre");
            tel = extras.getString("tel");
            // imagen.setImageURI(Uri.parse(extras.getString("img")));
        }
        tvNombre.setText(nombre);
        tvTel.setText(tel);
        etNombre = findViewById(R.id.etNombre);
        etTel = findViewById(R.id.etTel);

        boton = findViewById(R.id.vuelta);
        boton.setOnClickListener(this);

        borrar = findViewById(R.id.borrar);
        borrar.setOnClickListener(this);

        modificar = findViewById(R.id.modificar);
        modificar.setOnClickListener(this);
        imagen = findViewById(R.id.imagen);
        imagen.setOnClickListener(this);
        etNombre.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        etTel.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                llamar();
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(this, "Necesitamos permiso para llamar", Toast.LENGTH_SHORT).show();
            }
        });

        imgResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                uriCapturada = data.getData();
                getContentResolver().takePersistableUriPermission(uriCapturada, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                String s = uriCapturada.toString();
                imagen.setImageURI(Uri.parse(s));
            }
        });


    }

    public void createORM() {
        //Se crea la base de datos
        db = Room.databaseBuilder(getApplicationContext(), appDataBase.class, "tablaPersona").allowMainThreadQueries().build();
        personaDAO = db.personaDAO();//extrae datos de la bd para darle al dao?

        //extr4e el contenido de la bd
        List<tablaPersona> personaBD = personaDAO.getAll();//le estoy metiendo
        //el objeto, la lista, está extrayendo todas las tuplas
        //y se la meto al objeto uwu

        //Recorre la base e anade al ArrayList utilizado
        //mirar como funciona for each:
        //primer valor tabla, segundo valor objeto bd
    }

    @Override
    public void onClick(View view) {
        metodos(view.getId());
    }

    private void metodos(int id) {
        switch (id) {
            case R.id.vuelta:
                retornar();
                break;
            case R.id.modificar:

                //Extrae el total de la base de datos y comprueba si coincide
                personaBD = personaDAO.getAll();
                String vNom = etNombre.getText().toString();
                String vTel = etTel.getText().toString();
                for (tablaPersona a /*objeto vacio de la BD*/: personaBD) {
                    if (a.nombre.equals(tvNombre.getText().toString())) {
                        a.nombre = vNom;
                        a.telefono = vTel;
                        a.imagen = uriCapturada.toString();
                        if (a.nombre.equals("")) {
                            etNombre.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                        } else {
                            etNombre.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        }
                        if (a.telefono.equals("")) {
                            etTel.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                        } else {
                            etTel.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        }
                        if (!(a.nombre.equals("")) && !(a.telefono.equals(""))) {
                            personaDAO.updateRecord(a);
                            etNombre.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                            etTel.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                            Toast.makeText(getApplicationContext(), "Has modificado un individuo", Toast.LENGTH_SHORT).show();
                            retornar();
                        } else {
                            Toast.makeText(getApplicationContext(), "Datos vacíos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.borrar:
                personaBD = personaDAO.getAll();
                for (tablaPersona a /*objeto vacio de la BD*/: personaBD) {
                    if (a.nombre.equals(tvNombre.getText().toString())) {
                        personaDAO.delete(a);
                    }
                }
                Toast.makeText(getApplicationContext(), "Has borrado un individuo", Toast.LENGTH_SHORT).show();
                retornar();
                break;
            case R.id.imagen:

                metelImagen();

                break;

        }
    }

    public void metelImagen() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uriCapturada);
        imgResult.launch(i);
    }


    public void retornar() {
        //

        intent = new Intent(this, mainAgenda.class);
        setResult(PARAFILTRAR, intent);
        finish();
    }

    private void llamar() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        //sin el tel: no entiende que es un numero de telefono
        phoneIntent.setData(Uri.parse("tel:+34" + tvTel.getText().toString()));
        startActivity(phoneIntent);
    }

    public void llamadaClick(View v) {
        if (ContextCompat.checkSelfPermission(perfilPersona.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            llamar();
        } else if (false) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined. In this UI, include a
            // "cancel" or "no thanks" button that lets the user continue
            // using your app without granting the permission.

            // Mostrar UI Dialog para explicar al usuarios la necesidad del permiso
            // Vamos a usar la de por defecto de Android. Se ejecuta en el else

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
        }
    }


}
