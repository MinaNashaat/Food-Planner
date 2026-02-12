package com.mina.foodplanner.data.datasource.ingredients.remote;

import com.mina.foodplanner.data.model.Ingredients;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientsAPIService {
    @GET("list.php?i=list")
    Single<Ingredients> getAllIngredients();
}
