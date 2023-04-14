package com.example.examen1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText n1,n2;
    ActivityResultLauncher recibidorLauncher;
    Button calculate;
    RadioButton add,substract,multiply,divide;
    Intent intento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1=findViewById(R.id.n1);
        n2=findViewById(R.id.n2);
        calculate=findViewById(R.id.calculate);
        add=findViewById(R.id.add);
        substract=findViewById(R.id.substract);
        multiply=findViewById(R.id.multiply);
        divide=findViewById(R.id.divide);
        calculate.setOnClickListener(this);
        launcher();


    }
    public void launcher(){
        recibidorLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    if(result.getResultCode()==10){
                    add.setChecked(false);
                    substract.setChecked(false);
                    divide.setChecked(false);
                    multiply.setChecked(false);
                    Toast.makeText(this, "Vuelta a la actividad principal", Toast.LENGTH_SHORT).show();}
                    if(result.getResultCode()==20){
                        n1.setText("");
                        n2.setText("");
                        add.setChecked(false);
                        substract.setChecked(false);
                        divide.setChecked(false);
                        multiply.setChecked(false);
                        Toast.makeText(this, "Vuelta a la actividad principal", Toast.LENGTH_SHORT).show();}


                });
    }
    @Override
    public void onClick(View view) {
        pulsado(view.getId());
    }
    public void pulsado(int opc){
        switch(opc){
            case R.id.calculate:
                if(add.isChecked()) {
                    if (n1.getText().toString().equals("") || n2.getText().toString().equals("")) {
                        Toast.makeText(this, "nada", Toast.LENGTH_SHORT).show();
                    } else {
                        intento = new Intent(getApplicationContext(), Resultado.class);
                        intento.putExtra("N1", n1.getText().toString());
                        intento.putExtra("N2", n2.getText().toString());
                        intento.putExtra("Op", "+");
                        recibidorLauncher.launch(intento);
                    }
                }
                if(substract.isChecked()) {
                    if (n1.getText().toString().equals("") || n2.getText().toString().equals("")) {
                        Toast.makeText(this, "nada", Toast.LENGTH_SHORT).show();
                    } else {
                        intento = new Intent(getApplicationContext(), Resultado.class);
                        intento.putExtra("N1", n1.getText().toString());
                        intento.putExtra("N2", n2.getText().toString());
                        intento.putExtra("Op", "-");
                        recibidorLauncher.launch(intento);
                    }
                }
                if(multiply.isChecked()){
                    if(n1.getText().toString().equals("")||n2.getText().toString().equals("")){
                        Toast.makeText(this, "nada", Toast.LENGTH_SHORT).show();
                    }else{
                    intento=new Intent(getApplicationContext(),Resultado.class);
                    intento.putExtra("N1",n1.getText().toString());
                    intento.putExtra("N2",n2.getText().toString());
                    intento.putExtra("Op","*");
                    recibidorLauncher.launch(intento);
                    }
                }
                if(divide.isChecked()){
                    if(n1.getText().toString().equals("")||n2.getText().toString().equals("")||n2.getText().toString().equals("0")){
                        Toast.makeText(this, "nada", Toast.LENGTH_SHORT).show();
                    }else{
                    intento=new Intent(getApplicationContext(),Resultado.class);
                    intento.putExtra("N1",n1.getText().toString());
                    intento.putExtra("N2",n2.getText().toString());
                    intento.putExtra("Op","/");
                    recibidorLauncher.launch(intento);}
                }













                break;
        }
    }
}