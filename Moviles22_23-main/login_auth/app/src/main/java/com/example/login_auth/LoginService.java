package com.example.login_auth;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface LoginService {
    @GET("polls/api/question/{pk}")
    Call<Question> getQuestion(@Path("pk") int pk);
    }

