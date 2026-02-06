package com.mina.foodplanner.data.network;

import com.mina.foodplanner.MealsAPIService;
import com.mina.foodplanner.data.datasource.home.remote.HomeAPIService;
import com.mina.foodplanner.data.datasource.search.remote.SearchAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Retrofit retrofit;
    private static Network INSTANCE;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealsAPIService mealsAPIService;

    public MealsAPIService getMealsAPIService() {
        return retrofit.create(MealsAPIService.class);
    }
    public HomeAPIService getHomeAPIService() {
        return retrofit.create(HomeAPIService.class);
    }
    public SearchAPIService getSearchAPIService() {
        return retrofit.create(SearchAPIService.class);
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
