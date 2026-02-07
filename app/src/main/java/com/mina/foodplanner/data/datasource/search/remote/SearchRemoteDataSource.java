package com.mina.foodplanner.data.datasource.search.remote;

import android.util.Log;

import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.Meals;
import com.mina.foodplanner.data.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRemoteDataSource {
    SearchAPIService searchAPIService;

    public SearchRemoteDataSource() {
        this.searchAPIService = Network.getInstance().getSearchAPIService();
    }
    public void searchMealsByName(String mealName, SearchByNameNetworkResponse callBack){
        Call<Meals> meals = searchAPIService.searchMealsByName(mealName);
        meals.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if(response.isSuccessful() && response.body()!= null){
                    List<Meal> meals = response.body().meals;
                    callBack.onSuccess(meals);

                }
                else{
                    callBack.onFailure("Error server");
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                if(t instanceof IOException){
                    callBack.noInternet();
                }
                else{
                    callBack.onFailure("Conversion error");
                }
            }
        });
    }
}
