package com.mina.foodplanner.data.network;

import com.mina.foodplanner.MealsAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Retrofit retrofit;
    private static Network INSTANCE;
    private static final String BASE_URL = "www.themealdb.com/api/json/v1/1/";
    private MealsAPIService mealsAPIService;

    public MealsAPIService getMealsAPIService() {
        return retrofit.create(MealsAPIService.class);
    }

    private Network(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Network getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Network();
        }
        return INSTANCE;
    }
}
