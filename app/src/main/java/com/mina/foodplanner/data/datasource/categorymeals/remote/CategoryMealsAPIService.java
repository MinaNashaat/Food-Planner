package com.mina.foodplanner.data.datasource.categorymeals.remote;

import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryMealsAPIService {
    @GET("filter.php")
    Call<FilterResult> filterMealsByCategory(@Query("c") String category);

    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String mealId);
}
