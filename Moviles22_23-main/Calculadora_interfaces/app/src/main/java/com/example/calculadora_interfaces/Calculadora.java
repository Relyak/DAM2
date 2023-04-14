package com.example.calculadora_interfaces;
//Estas son todas las clases que utilizaré para el funcionamiento
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora_interfaces.R;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {
    TextView resultado;
    Button borrar,sumar,restar,dividir,num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,igual,multi;
    float numero1= 0.0f;
    float numero2= 0.0f;
    float valor= 0.0f;
    String op="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);
        //aquí asigno los botones del layout a los del código y con el this hago que un solo listener ejecute todas las órdenes
        //dependiendo de que botón he pulsado
        resultado=findViewById(R.id.resultado);
        multi=findViewById(R.id.multi);
        multi.setOnClickListener(this);
        borrar=findViewById(R.id.borrar);
        borrar.setOnClickListener(this);
        sumar=findViewById(R.id.sumar);
        sumar.setOnClickListener(this);
        restar=findViewById(R.id.restar);
        restar.setOnClickListener(this);
        dividir=findViewById(R.id.dividir);
        dividir.setOnClickListener(this);
        num0=findViewById(R.id.num0);
        num0.setOnClickListener(this);
        num1=findViewById(R.id.num1);
        num1.setOnClickListener(this);
        num2=findViewById(R.id.num2);
        num2.setOnClickListener(this);
        num3=findViewById(R.id.num3);
        num3.setOnClickListener(this);
        num4=findViewById(R.id.num4);
        num4.setOnClickListener(this);
        num5=findViewById(R.id.num5);
        num5.setOnClickListener(this);
        num6=findViewById(R.id.num6);
        num6.setOnClickListener(this);
        num7=findViewById(R.id.num7);
        num7.setOnClickListener(this);
        num8=findViewById(R.id.num8);
        num8.setOnClickListener(this);
        num9=findViewById(R.id.num9);
        num9.setOnClickListener(this);
        igual=findViewById(R.id.igual);
        igual.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        principal(view.getId());
    }

    public void principal(int a){

        switch(a){
            case R.id.num0:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("0");
                }else{
                    resultado.setText(resultado.getText()+"0");
                }
                break;
            case R.id.num1:

                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("1");
                }else{
                    resultado.setText(resultado.getText()+"1");
                }
                break;
            case R.id.num2:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("2");
                }else{
                    resultado.setText(resultado.getText()+"2");
                }
                break;
            case R.id.num3:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("3");
                }else{
                    resultado.setText(resultado.getText()+"3");
                }
                break;
            case R.id.num4:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("4");
                }else{
                    resultado.setText(resultado.getText()+"4");
                }
                break;
            case R.id.num5:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("5");
                }else{
                    resultado.setText(resultado.getText()+"5");
                }
                break;
            case R.id.num6:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("6");
                }else{
                    resultado.setText(resultado.getText()+"6");
                }
                break;
            case R.id.num7:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("7");
                }else{
                    resultado.setText(resultado.getText()+"7");
                }
                break;
            case R.id.num8:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("8");
                }else{
                    resultado.setText(resultado.getText()+"8");
                }
                break;
            case R.id.num9:
                valor = Float.parseFloat(resultado.getText().toString());
                if(valor== 0.0f){
                    resultado.setText("9");
                }else{
                    resultado.setText(resultado.getText()+"9");
                }
                break;
            case R.id.sumar:
                numero1=Float.parseFloat(resultado.getText().toString());
                resultado.setText("0");
                op="+";
                break;
            case R.id.restar:
                numero1=Float.parseFloat(resultado.getText().toString());
                op="-";
                resultado.setText("0");
                break;
            case R.id.multi:
                numero1=Float.parseFloat(resultado.getText().toString());
                op="*";
                resultado.setText("0");
                break;
            case R.id.borrar:
                resultado.setText("0");
                numero1= 0.0f;
                numero2= 0.0f;
                break;
            case R.id.dividir:
                numero1=Float.parseFloat(resultado.getText().toString());
                op="/";
                resultado.setText("0");
                break;
            case R.id.igual:
                numero2=Float.parseFloat(resultado.getText().toString());
                if(op.equals("/")){
                    if(numero2==0.0f){
                        resultado.setText("0");
                        Toast.makeText(this, "Division entre 0",Toast.LENGTH_LONG).show();

                    }else{
                        float result= numero1/numero2;
                        resultado.setText(result+"");
                    }
                }
                if(op.equals("+")){

                    resultado.setText((int)(numero1+numero2)+"");
                }
                if(op.equals("-")){

                    resultado.setText((int)(numero1-numero2)+"");
                }
                if(op.equals("*")){

                    resultado.setText((int)(numero1*numero2)+"");
                }
                numero1=0.0f;
                numero2=0.0f;

                break;



        }
    }



}