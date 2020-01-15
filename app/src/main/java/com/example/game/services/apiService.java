package com.example.game.services;

import com.example.game.models.Item;
import com.example.game.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface apiService {


    @GET("users/{owner}")
    Call<User> getUser(@Path("owner") String owner);

    @GET("users/{owner}/objects")
    Call<List<Item>> getItems(@Path("owner") String owner);

    @POST("adduser")
    Call<User> createUser(@Body User user);

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("objects")
    Call<List<Item>> getAllObjetcs();

    @PUT("modifyUser")
    Call<ResponseBody> modifyUser(@Body User user);

}
