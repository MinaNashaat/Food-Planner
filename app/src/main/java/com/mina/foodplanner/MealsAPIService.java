package com.mina.foodplanner;

import com.mina.foodplanner.data.model.AllAreas;
import com.mina.foodplanner.data.model.AllCategories;
import com.mina.foodplanner.data.model.Categories;
import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.Ingredients;
import com.mina.foodplanner.data.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsAPIService {



    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String mealId);



    @GET("list.php?i=list")
    Call<Ingredients> getAllIngredients();





    @GET("filter.php")
    Call<FilterResult> filterMealsByArea(@Query("a") String area);


}
