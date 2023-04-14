package com.example.viaje;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class lanzador_viaje extends AppCompatActivity implements View.OnClickListener {
    //creo todos los elementos que voy a usar de la interfaz
    EditText nombre,dni,recogida,horaIda,horaVuelta,fechaIda,fechaVuelta;
    Button imprimir;
    CheckBox ida,ida_vuelta;
    Spinner origen,destino;
    TextView ejemplos;

    //booleans para comprobar si funciona o no
    boolean check_origen_destino=false;
    boolean check_dni=false;
    boolean check_comparar_fechas=false;
    boolean check_nombre=false;
    //patron de dni
    Pattern dni_nie_patron = Pattern.compile("^[XYZ]?([0-9]{7,8})([A-Z])$");
    cViaje datos=new cViaje();

    public static String IDENTIFICADOR_RECEPTOR = "CLAVE_PARA_MANDAR_A_RECEPTOR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanzador_viaje);
        //le doy señalo los ID de mi layout, a los que son click les asigno
        // el clickListener con this para tener un solo metodo para varios clicks
        //Edit text


        nombre=findViewById(R.id.nombre);
        dni=findViewById(R.id.dni);
        recogida=findViewById(R.id.recogida);
        horaIda=findViewById(R.id.horaIda);
        horaIda.setOnClickListener(this);
        horaVuelta=findViewById(R.id.horaVuelta);
        horaVuelta.setOnClickListener(this);
        fechaIda=findViewById(R.id.fechaIda);
        fechaIda.setOnClickListener(this);
        fechaVuelta=findViewById(R.id.fechaVuelta);
        fechaVuelta.setOnClickListener(this);

        //imprimir es mi boton
        imprimir=findViewById(R.id.imprimir);
        imprimir.setOnClickListener(this);


        //ida y vuelta son los checkbox
        ida=findViewById(R.id.ida);
        ida.setOnClickListener(this);
        ida_vuelta=findViewById(R.id.ida_vuelta);
        ida_vuelta.setOnClickListener(this);
        origen=findViewById(R.id.origen);
        destino=findViewById(R.id.destino);

        ejemplos=findViewById(R.id.ejemplos);
        ejemplos.setText("");

        ida.setChecked(true);
        origen.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        destino.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
    }
    @Override
    public void onClick(View view)  {

        pulsado(view.getId());

    }

    private void lanzar(){

        Intent intento =  new Intent(this, receptor_viaje.class);
       // intento.putExtra(IDENTIFICADOR_RECEPTOR, (pantalla()));
        intento.putExtra(IDENTIFICADOR_RECEPTOR,(Serializable) pantalla());
        startActivity(intento);
    }
    public cViaje pantalla(){
        if(ida.isChecked()){
            datos.setNombre(nombre.getText().toString());
            datos.setDni(dni.getText().toString());
            datos.setRecogida(recogida.getText().toString());
            datos.setFechaIda(fechaIda.getText().toString());
            datos.setHoraIda(horaIda.getText().toString());
            datos.setOrigen(origen.getSelectedItem().toString());
            datos.setDestino(destino.getSelectedItem().toString());
            datos.setFechaVuelta("");
            datos.setHoraVuelta("");


            return datos;
        }else{
            datos.setNombre(nombre.getText().toString());
            datos.setDni(dni.getText().toString());
            datos.setRecogida(recogida.getText().toString());
            datos.setFechaIda(fechaIda.getText().toString());
            datos.setHoraIda(horaIda.getText().toString());
            datos.setFechaVuelta(fechaVuelta.getText().toString());
            datos.setHoraVuelta(horaVuelta.getText().toString());
            datos.setOrigen(origen.getSelectedItem().toString());
            datos.setDestino(destino.getSelectedItem().toString());
        return datos;
    }
}


    public void pulsado(int opcion) {
        switch (opcion){

            case R.id.imprimir:
                prueba();
                break;
            case R.id.ida:
                casoIda();
                break;
            case R.id.ida_vuelta:
                casoVuelta();
                break;
            case R.id.fechaIda:

                showDatePickerDialog(fechaIda);
                break;
            case R.id.fechaVuelta:
                //probar usar fecha como getText funciona
                showDatePickerDialog(fechaVuelta);
                break;
            case R.id.horaIda:
                showTimePickerDialog(horaIda);
                break;
            case R.id.horaVuelta:
                showTimePickerDialog(horaVuelta);
                break;
        }
    }
    private void showDatePickerDialog( EditText texto_sustitutivo) {
        DatePickerFragment newFragment= DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fechaSeleccionada= dia+ "/" + (mes+1)+"/"+ anio;
                texto_sustitutivo.setText(fechaSeleccionada);
            }
        });
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    private void showTimePickerDialog( EditText texto_sustitutivo) {
        TimePickerFragment newFragment= TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {



            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                final String horaSeleccionada= hora+ ":" +minuto;
                texto_sustitutivo.setText(horaSeleccionada);
            }
        });
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    private boolean comprobarDestino(){
        int o= origen.getSelectedItemPosition();
        int d=destino.getSelectedItemPosition();

        if((o==d)||(o==0)||(d==0)){
            return check_origen_destino=false;
        }else{
            destino.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            origen.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            return check_origen_destino=true;
        }

    }
    private boolean comprobarDni(){
        Matcher comprobar_dni_nie= dni_nie_patron.matcher(dni.getText().toString().toUpperCase());
        if(!comprobar_dni_nie.matches()){

            return check_dni=false;
        }else{
            return check_dni=true;
        }
    }
    private void casoIda(){
        ida.setChecked(true);
        ida_vuelta.setChecked(false);
        fechaVuelta.setText("");
        horaVuelta.setText("");
        fechaVuelta.setVisibility(View.INVISIBLE);
        horaVuelta.setVisibility(View.INVISIBLE);
    }
    private void casoVuelta(){
        ida_vuelta.setChecked(true);
        ida.setChecked(false);
        fechaVuelta.setVisibility(View.VISIBLE);
        horaVuelta.setVisibility(View.VISIBLE);
    }
    private boolean comprobarFechas(){
        SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaDeIda= formato.parse(fechaIda.getText().toString());
            Date fechaDeVuelta= formato.parse(fechaVuelta.getText().toString());
            if (ida_vuelta.isChecked()&&(fechaDeIda.after(fechaDeVuelta))){
                fechaVuelta.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                return check_comparar_fechas=false;

            }else{
                return check_comparar_fechas=true;
            }
        } catch (ParseException e) {
            fechaVuelta.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            e.printStackTrace();
            return false;
        }

    }
    private void prueba() {
        if(
                ((ida.isChecked()&&comprobarNombre()&&comprobarDni()&&comprobarDestino()))||
                        ((ida_vuelta.isChecked()&&comprobarNombre()&&comprobarDni()&&comprobarDestino()&&comprobarFechas()))

        ){
            lanzar();
        }else{
            Toast.makeText(this, "Comprueba todos los datos", Toast.LENGTH_SHORT).show();
        }





    }
    private boolean comprobarNombre(){
        if(nombre.getText().toString()==""){
            nombre.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            return check_nombre=false;
        }else{
            return check_nombre=true;
        }
    }
}