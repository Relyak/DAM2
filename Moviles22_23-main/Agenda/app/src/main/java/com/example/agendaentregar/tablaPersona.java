package com.example.agendaentregar;

import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName="tablaPersona")
public class tablaPersona implements Serializable {
    @PrimaryKey
    @NotNull
    public String uid;

    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "telefono")
    public String telefono;

    @ColumnInfo(name = "imagen")
    public String imagen;


    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }


    public String getTel() {
        return telefono;
    }



    public tablaPersona(){ uid= UUID.randomUUID().toString();}
    public tablaPersona(String nombre,String telefono,String imagen) {
        this.nombre = nombre;
        this.telefono=telefono;
        this.imagen=imagen;
    }

}