package com.mina.foodplanner.data.datasource.home.remote;

import com.mina.foodplanner.data.model.Categories;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.Meals;
import com.mina.foodplanner.data.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRemoteDataSource {

    HomeAPIService homeAPIService;

    public HomeRemoteDataSource(){
        this.homeAPIService = Network.getInstance().getHomeAPIService();
    }

    public Single<Meals> getMealDay(){
        return homeAPIService.getRandomMeal();
//        meals.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    Meal randomMeal = response.body().meals.get(0);
//                    callBack.onSuccess(randomMeal);
//                }
//                else{
//                    callBack.onFailure("Error server");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Meals> call, Throwable t) {
//                if(t instanceof IOException){
//                    callBack.noInternet();
//                }
//                else{
//                    callBack.onFailure("Conversion error");
//                }
//            }
//        });
    }

    public Single<Categories> getAllCategories(){

        return homeAPIService.getAllCategories();
//        categories.enqueue(new Callback<Categories>() {
//            @Override
//            public void onResponse(Call<Categories> call, Response<Categories> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    List<Category> categoriesList = response.body().categories;
//                    callBack.onSuccess(categoriesList);
//                }
//                else{
//                    callBack.onFailure("Error server");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Categories> call, Throwable t) {
//                if(t instanceof IOException){
//                    callBack.noInternet();
//                }
//                else{
//                    callBack.onFailure("Conversion error");
//                }
//            }
//        });

    }
}
