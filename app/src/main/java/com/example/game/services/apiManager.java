package com.example.game.services;

import com.example.game.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiManager {

    private static apiService apiService;
    private static apiManager apiManager;

    private apiManager() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://147.83.7.206:8080/dsaApp/").addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(apiService.class);
    }

    public static apiManager getInstance(){
        if(apiManager == null){
            apiManager = new apiManager();
        }
        return apiManager;
    }

    public void createUser(User user, Callback<User> callback){
        Call<User> call = apiService.createUser(user);
        call.enqueue(callback);
    }

    public void buyObject(int price, Item item){

    }

    public void loginUser(String username, Callback<User> callback){
        Call<User> call  = apiService.getUser(username);
        call.enqueue(callback);
    }

    public void getAllObjects(Callback<List<Item>> callback){
        Call<List<Item>> call = apiService.getAllObjetcs();
        call.enqueue(callback);
    }
}
