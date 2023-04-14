package com.example.agendaentregar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonaDAO {
    @Query("SELECT * FROM tablaPersona")
    List<tablaPersona> getAll();

    @Query("SELECT * FROM tablaPersona WHERE uid IN (:userIds)")
    List<tablaPersona> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM tablaPersona WHERE nombre LIKE :nombre LIMIT 1")
    tablaPersona findByName(String nombre);



    @Insert
    void insertAll(tablaPersona users);

    @Delete
    void delete(tablaPersona user);
    @Update
    void updateRecord(tablaPersona user);

}