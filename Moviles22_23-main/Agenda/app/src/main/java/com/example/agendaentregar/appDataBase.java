package com.example.agendaentregar;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {tablaPersona.class}, version = 1)
public abstract class appDataBase extends RoomDatabase {
    public abstract PersonaDAO personaDAO();
}