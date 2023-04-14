package com.example.viaje;

import java.io.Serializable;


public class cViaje implements Serializable {
    private String nombre;
    private String dni;
    private String recogida;
    private String fechaIda;
    private String horaIda;
    private String fechaVuelta;
    private String horaVuelta;
    private String Origen;
    private String Destino;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRecogida() {
        return recogida;
    }

    public void setRecogida(String recogida) {
        this.recogida = recogida;
    }

    public String getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    public String getHoraIda() {
        return horaIda;
    }

    public void setHoraIda(String horaIda) {
        this.horaIda = horaIda;
    }

    public String getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(String fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public String getHoraVuelta() {
        return horaVuelta;
    }

    public void setHoraVuelta(String horaVuelta) {
        this.horaVuelta = horaVuelta;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    @Override
    public String toString () {
        return
                "NOMBRE: " + nombre + '\n' +
                "DNI: " + dni + '\n' +
                "RECOGIDA: " + recogida + '\n' +
                "FECHA IDA: " + fechaIda + '\n' +
                "HORA IDA: " + horaIda + '\n' +
                "ORIGEN: " + Origen + '\n' +
                "DESTINO: " + Destino+ '\n'+
                "FECHA VUELTA: " + fechaVuelta + '\n' +
                "HORA VUELTA: " + horaVuelta;
    }
}