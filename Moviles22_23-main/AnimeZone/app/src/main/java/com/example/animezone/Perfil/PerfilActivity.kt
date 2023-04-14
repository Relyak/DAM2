package com.example.animezone.Perfil

import android.content.DialogInterface
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.animezone.Clase.Usuario
import com.example.animezone.ProgressBar.CargandoDialog
import com.example.animezone.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_registro.*

class PerfilActivity : AppCompatActivity() {
    private var contador = 1
    private val baseDatos = Firebase.firestore
    private val autentificacion = Firebase.auth
    private val coleccionUsuarios = baseDatos.collection("usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val cargando = CargandoDialog(this)
        //Enseñar ProgressBar
        cargando.empezarCarga()
        val handler = Handler()
        handler.postDelayed({ cargando.cancelable() }, 1900)

        /*val usuario: Usuario = Usuario(
            campoNombreTextoPerfil,
            campoApellidosTextoPerfil,
            campoCorreoTextoPerfil,
            campoNombreIdTextoPerfil,
            campoContrasenaPerfil
        )*/

        //Enseñar Campos
        coleccionUsuarios.document(autentificacion.currentUser.displayName).get()
            .addOnSuccessListener {
                nombrePerfil_texto.setText(it.getString("nombreUsuario").toString())
                apellidosPerfil_texto.setText(it.getString("apellidos").toString())
                correoPerfil_texto.setText(it.getString("correo").toString())
                nicknamePerfil_texto.setText(it.getString("usuarioId").toString())
                contrasenaPerfil_texto.setText(it.getString("contrasena").toString())
                var urlImagen = it.getString("imagen").toString()
                Glide.with(this)
                    .load(urlImagen)
                    .fitCenter()
                    .into(imagenPerfil)
            }

        var campoNombreTextoPerfil = nombrePerfil_texto.text.toString()
        var campoApellidosTextoPerfil = apellidosPerfil_texto.text.toString()
        var campoCorreoTextoPerfil = correoPerfil_texto.text.toString()
        var campoNombreIdTextoPerfil = nicknamePerfil_texto.text.toString()
        var campoContrasenaPerfil = contrasenaPerfil_texto.text.toString()
        val usuario: Usuario = Usuario(campoNombreTextoPerfil, campoApellidosTextoPerfil, campoCorreoTextoPerfil, campoNombreIdTextoPerfil, campoContrasenaPerfil, autentificacion.currentUser.photoUrl.toString())
        //Para q este desactivado tiene q ser el desactivar false
        editarPerfil_btn.setOnClickListener {
            if (contador % 2 != 0) {
                camposPerfil(true)
                //Toast.makeText(this, autentificacion.currentUser.email, Toast.LENGTH_SHORT).show()
                editarPerfil_btn.setText("Guardar Cambios")
            } else {
                /*coleccionUsuarios.document(campoNombreIdTextoPerfil).set(usuario)
                    .addOnSuccessListener {
                        AlertDialog.Builder(this).apply {
                            setTitle("Actualizado Correctamente")
                            setMessage("Los cambios fueron buenos")
                            setPositiveButton(Html.fromHtml("<font color='#FFFFFF'>Aceptar</font>")) { _: DialogInterface, _: Int ->
                            }
                        }.show()
                    }*/
                camposPerfil(false)
                editarPerfil_btn.setText("Editar Perfil")
            }
            contador++
        }
    }
    private fun camposPerfil(desactivador: Boolean) {
        nombrePerfil_texto.isEnabled = desactivador
        apellidosPerfil_texto.isEnabled = desactivador
        correoPerfil_texto.isEnabled = desactivador
        nicknamePerfil_texto.isEnabled = desactivador
        contrasenaPerfil_texto.isEnabled = desactivador
    }
}