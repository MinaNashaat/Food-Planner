package com.mina.foodplanner.data.datasource.ingredients.remote;

import android.util.Log;

import com.mina.foodplanner.data.model.Ingredient;
import com.mina.foodplanner.data.model.Ingredients;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.network.Network;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsRemoteDataSource {
    IngredientsAPIService ingredientsAPIService;

    public IngredientsRemoteDataSource() {
        this.ingredientsAPIService = Network.getInstance().getIngredientsAPIService();
    }

    public Single<Ingredients> getAllIngredients(){
        return ingredientsAPIService.getAllIngredients();
//        Call<Ingredients> ingredients = ingredientsAPIService.getAllIngredients();
//        ingredients.enqueue(new Callback<Ingredients>() {
//            @Override
//            public void onResponse(Call<Ingredients> call, Response<Ingredients> response) {
//                if(response.isSuccessful() && response.body()!= null){
//                    List<Ingredient> ingredientsList = response.body().ingredients;
//                    callBack.onSuccess(ingredientsList);
//                }
//                else{
//                    callBack.onFailure("Error server");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Ingredients> call, Throwable t) {
//
//            }
//        });
    }

}
