package com.mina.foodplanner.data.datasource.filteredmeals.remote;

import android.util.Log;

import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.Meals;
import com.mina.foodplanner.data.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredMealsRemoteDataSource {
    FilteredMealsAPIService categoryMealsAPIService;

    public FilteredMealsRemoteDataSource() {
        this.categoryMealsAPIService = Network.getInstance().getCategoryMealsAPIService();
    }

    public void getCategoryMeals(String categoryMealName, FilteredMealsNetworkResponse callBack){
        Call<FilterResult> meals =  categoryMealsAPIService.filterMealsByCategory(categoryMealName);
        meals.enqueue(new Callback<FilterResult>() {
            @Override
            public void onResponse(Call<FilterResult> call, Response<FilterResult> response) {
                if(response.isSuccessful() && response.body()!= null){
                    List<FilteredMeal> filteredMeals = response.body().filteredMeals;
                    callBack.onSuccess(filteredMeals);
                    Log.d("minanashaat" , "yaraaaab" + filteredMeals.size());
                }
                else{
                    callBack.onFailure("Error server");
                }
            }

            @Override
            public void onFailure(Call<FilterResult> call, Throwable t) {
                if(t instanceof IOException){
                    callBack.noInternet();
                }
                else{
                    callBack.onFailure("Conversion error");
                }
            }
        });
    }

    public void getIngredientMeals(String ingredientName, FilteredMealsNetworkResponse callBack){
        Call<FilterResult> meals =  categoryMealsAPIService.filterMealsByIngredient(ingredientName);
        meals.enqueue(new Callback<FilterResult>() {
            @Override
            public void onResponse(Call<FilterResult> call, Response<FilterResult> response) {
                if(response.isSuccessful() && response.body()!= null){
                    List<FilteredMeal> filteredMeals = response.body().filteredMeals;
                    callBack.onSuccess(filteredMeals);
                    Log.d("minanashaat" , "yaraaaab" + filteredMeals.size());
                }
                else{
                    callBack.onFailure("Error server");
                }
            }

            @Override
            public void onFailure(Call<FilterResult> call, Throwable t) {
                if(t instanceof IOException){
                    callBack.noInternet();
                }
                else{
                    callBack.onFailure("Conversion error");
                }
            }
        });
    }

    public void getMealById(String mealID, MealByIDNetworkResponse callBack){
        Call<Meals> mealsCall = categoryMealsAPIService.getMealById(mealID);
        mealsCall.enqueue(new Callback<Meals>() {
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
