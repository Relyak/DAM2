package com.example.examen1;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    String num1;
    String num2;
    String op;
    TextView resultado;
    Button reset;
    Button volver;
    Intent intent;
    public static int VOLVER =10;
    public static int RESET =20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        resultado=findViewById(R.id.resultado);
        Bundle extras= getIntent().getExtras();
        if(extras !=null){
            num1=extras.getString("N1");
            num2=extras.getString("N2");
            op=extras.getString("Op");
            int n1=0,n2=0;
            int calculo;
            n1=Integer.parseInt(num1);
            n2=Integer.parseInt(num2);

            switch(op){
                case "+":
                    calculo=n1+n2;
                    resultado.setText(calculo+"");
                    break;
                case "-":
                    calculo=n1-n2;
                    resultado.setText(calculo+"");
                    break;
                case "*":
                    calculo=n1*n2;
                    resultado.setText(calculo+"");
                    break;
                case "/":
                    calculo=n1/n2;
                    resultado.setText(calculo+"");
                    break;
            }
        }
        volver=findViewById(R.id.Volver);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(v->{
            intent = new Intent(this,MainActivity.class);
            setResult(RESET,intent);
            finish();}
        );

        volver.setOnClickListener(v->{
                 intent = new Intent(this,MainActivity.class);
                setResult(VOLVER,intent);
                finish();}
                );



    }

}