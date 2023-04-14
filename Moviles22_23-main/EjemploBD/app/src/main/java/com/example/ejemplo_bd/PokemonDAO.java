package com.example.ejemplo_bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Query("SELECT * FROM pokemon")
    List<Pokemon> getAll();

    @Insert
    void insertAll(Pokemon... users);



    @Delete
    void delete(Pokemon user);
}
