package com.example.ejemplo_bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pokemon.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonDAO pokedexDAO();
}
