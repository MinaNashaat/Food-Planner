package com.mina.foodplanner.data.datasource.filteredmeals.remote;

import android.util.Log;

import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.Meals;
import com.mina.foodplanner.data.network.Network;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredMealsRemoteDataSource {
    FilteredMealsAPIService categoryMealsAPIService;

    public FilteredMealsRemoteDataSource() {
        this.categoryMealsAPIService = Network.getInstance().getCategoryMealsAPIService();
    }

    public Single<FilterResult> getCategoryMeals(String categoryMealName){
        return categoryMealsAPIService.filterMealsByCategory(categoryMealName);
//        meals.enqueue(new Callback<FilterResult>() {
//            @Override
//            public void onResponse(Call<FilterResult> call, Response<FilterResult> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    List<FilteredMeal> filteredMeals = response.body().filteredMeals;
//                    callBack.onSuccess(filteredMeals);
//                    Log.d("minanashaat" , "yaraaaab" + filteredMeals.size());
//                }
//                else{
//                    callBack.onFailure("Error server");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilterResult> call, Throwable t) {
//                if(t instanceof IOException){
//                    callBack.noInternet();
//                }
//                else{
//                    callBack.onFailure("Conversion error");
//                }
//            }
//        });
    }

    public Single<FilterResult> getIngredientMeals(String ingredientName){
        return categoryMealsAPIService.filterMealsByIngredient(ingredientName);
//        meals.enqueue(new Callback<FilterResult>() {
//            @Override
//            public void onResponse(Call<FilterResult> call, Response<FilterResult> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    List<FilteredMeal> filteredMeals = response.body().filteredMeals;
//                    callBack.onSuccess(filteredMeals);
//                    Log.d("minanashaat" , "yaraaaab" + filteredMeals.size());
//                }
//                else{
//                    callBack.onFailure("Error server");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilterResult> call, Throwable t) {
//                if(t instanceof IOException){
//                    callBack.noInternet();
//                }
//                else{
//                    callBack.onFailure("Conversion error");
//                }
//            }
//        });
    }

    public Single<FilterResult> getAreaMeals(String area){
        return categoryMealsAPIService.filterMealsByArea(area);
//        meals.enqueue(new Callback<FilterResult>() {
//            @Override
//            public void onResponse(Call<FilterResult> call, Response<FilterResult> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callBack.onSuccess(response.body().filteredMeals);
//                } else {
//                    callBack.onFailure("Server error");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilterResult> call, Throwable t) {
//                if (t instanceof IOException) {
//                    callBack.noInternet();
//                } else {
//                    callBack.onFailure("Conversion error");
//                }
//            }
//        });
    }



    public Single<Meals> getMealById(String mealID){
        return categoryMealsAPIService.getMealById(mealID);
//        mealsCall.enqueue(new Callback<Meals>() {
//            @Override
//            public void onResponse(Call<Meals> call, Response<Meals> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    List<Meal> meals = response.body().meals;
//                    callBack.onSuccess(meals);
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
}
