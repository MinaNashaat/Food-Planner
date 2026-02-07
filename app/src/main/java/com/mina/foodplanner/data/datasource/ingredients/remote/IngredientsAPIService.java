package com.mina.foodplanner.data.datasource.ingredients.remote;

import com.mina.foodplanner.data.model.Ingredients;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientsAPIService {
    @GET("list.php?i=list")
    Call<Ingredients> getAllIngredients();
}
