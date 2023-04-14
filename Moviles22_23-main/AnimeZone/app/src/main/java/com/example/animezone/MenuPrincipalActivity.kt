package com.example.animezone

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.animezone.Perfil.PerfilActivity
import com.example.animezone.Publicaciones.ListaPublicacionesActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu_lateral.*
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {
    private val autentificacion = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //Con esto llamo a mi cardview de la activity de MenuLateral
        val menuLateral : CardView = findViewById(R.id.MenuLateralCerrarSesion)

        val menuLateralPerfil:CardView=findViewById(R.id.MenuLateralPerfil)
        menuLateralPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
        //Y desde el Menu Principal hay que hacer los setonclickListener, si lo haces desde la activity MenuLateralActivity no te va el setonclicklistener
        menuLateral.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Cierre de Sesión")
                setMessage("¿Estás seguro que quieres cerrar sesión?")
                setPositiveButton(Html.fromHtml("<font color='#FFFFFF'>Si</font>")) { dialogInterface: DialogInterface, i: Int ->
                    autentificacion.signOut()
                    finish()
                }
                setNegativeButton(Html.fromHtml("<font color='#FFFFFF'>No</font>"), null)
            }.show()
        }

        //Sustituyo el valor del TextView, respecto al usuario que esta conectado y su imagen de Perfil
        cargarDatosPersonales()

        //Abrir Menu Lateral
        abrirMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        //Para ir a la lista de Todas las publicaciones
        listaPublicaciones.setOnClickListener {
            val intent = Intent(this, ListaPublicacionesActivity::class.java)
            startActivity(intent)
        }
        cerrarSesion()
    }

    private fun cargarDatosPersonales() {
        usuarioActual.text = autentificacion.currentUser.displayName
        Glide.with(this)
            .load(autentificacion.currentUser.photoUrl)
            .fitCenter()
            .into(imagenPerfilMenu)
    }

    private fun cerrarSesion() {
        cerrarSesion.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Cierre de Sesión")
                setMessage("¿Estás seguro que quieres cerrar sesión?")
                setPositiveButton(Html.fromHtml("<font color='#FFFFFF'>Si</font>")) { dialogInterface: DialogInterface, i: Int ->
                    autentificacion.signOut()
                    finish()
                }
                setNegativeButton(Html.fromHtml("<font color='#FFFFFF'>No</font>"), null)
            }.show()
        }
    }


}