package com.example.logininterfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login_interfaces extends AppCompatActivity {
    Button boton;
    Intent intentItfc;
    EditText correo,contrasena;
    TextView usuarioContrasena;
    final String USUARIO="1234";
    final String CONTRASENA="1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interfaces);
        boton=findViewById(R.id.button);
        correo=findViewById(R.id.correoInterfaz);
        contrasena=findViewById(R.id.contrasenaInterfaz);
        usuarioContrasena=findViewById(R.id.usuarioContrasena);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentItfc=new Intent(getApplicationContext(),otraInterfaz.class);
                if((correo.getText().toString().equals(USUARIO))&&(contrasena.getText().toString().equals(CONTRASENA))){
                    usuarioContrasena.setText("");
                    startActivity(intentItfc);}else{
                    usuarioContrasena.setText("Usuario o contraseña incorrecta");
                }
            }
        });
    }
}