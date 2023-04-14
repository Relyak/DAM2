package com.example.login_auth;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Usuario {
    public String user,passw;
    public String URL="http://192.168.1.137:8000/polls/api/question";
    public Usuario(String user, String passw) {
        this.user = user;
        this.passw = passw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
    private String token;

    // ... constructor y métodos set/get


    
}
