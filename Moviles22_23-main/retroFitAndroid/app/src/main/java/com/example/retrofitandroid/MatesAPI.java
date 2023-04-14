package com.example.retrofitandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatesAPI {
    private static MatesAPI instancia=null;
    MatesService service;
    private MatesAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.109.131:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service =retrofit.create(MatesService.class);
    }
    public static MatesAPI getInstance(){
        if(instancia==null){
            instancia=new MatesAPI();
        }
        return instancia;
    }
}
