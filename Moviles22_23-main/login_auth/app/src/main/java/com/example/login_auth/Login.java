package com.example.login_auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    //Variables
    EditText etUser,etPassw;
    Button btnEnviar;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etUser=findViewById(R.id.etUser);
        etPassw=findViewById(R.id.etPassw);
        btnEnviar=findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario=new Usuario(String.valueOf(etUser.getText()),String.valueOf(etPassw.getText().toString()));
                Log.d("Juan",usuario.getUser()+" "+usuario.getPassw());
                //usuario.requestToken(usuario.getUser(),usuario.getPassw());
                //usuario.enviarDatosAlServidor(usuario.getUser(),usuario.getPassw());

            }
        });






    }
}