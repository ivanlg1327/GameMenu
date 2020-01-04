package com.example.game.services;

import com.example.game.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userManager {

    private static userService userService;
    private static userManager userManager;

    private userManager() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://147.83.7.206:8080/dsaApp/").addConverterFactory(GsonConverterFactory.create()).build();

        userService = retrofit.create(userService.class);
    }

    public static  userManager getInstance(){
        if(userManager == null){
            userManager = new userManager();
        }
        return userManager;
    }

    public void createUser(User user, Callback<User> callback){
        Call<User> call = userService.createUser(user);
        call.enqueue(callback);
    }

    public void buyObject(int price, Item item){

    }

    public void loginUser(String username, Callback<User> callback){
        Call<User> call  = userService.getUser(username);
        call.enqueue(callback);
    }
}
