package com.example.game.services;

import com.example.game.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface userService {


    @GET("users/{owner}")
    Call<User> getUser(@Path("owner") String owner);


    /*
    public static userService getInstance() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://147.83.7.206:8080/dsaApp").addConverterFactory(GsonConverterFactory.create(gson)).build();

        return retrofit.create(userService.class);
    }
     */


}
