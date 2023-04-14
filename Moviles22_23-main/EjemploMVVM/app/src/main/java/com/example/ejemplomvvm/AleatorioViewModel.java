package com.example.ejemplomvvm;

import android.graphics.ColorSpace;
import android.view.Display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AleatorioViewModel extends ViewModel {
    MutableLiveData<Integer> datoObservable;
    ModelAleatorio datos;
    public LiveData<Integer> getDatoAleatorio(){
        if (datoObservable == null) {
            datoObservable = new MutableLiveData<Integer>();
            //EN ANDROID TENIAN UN LOADUSERS() EN NUESTRO CASO UN generarAleatorio()
            datos=new ModelAleatorio();
        }
        return datoObservable;
    }

    public void nuevoAleatorio(){
        //Actividad asíncrona//pedir al "servidor remoto"/esperar
        new Thread(() -> {
            //petición a servidor remoto
            datos.generarAleatorio();
            //que se entere que ya llegó el dato
            datoObservable.postValue(datos.getAleatorio());
        }).start();
    }

}