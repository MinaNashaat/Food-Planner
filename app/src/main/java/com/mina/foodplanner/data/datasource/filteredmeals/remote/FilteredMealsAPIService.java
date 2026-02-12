package com.mina.foodplanner.data.datasource.filteredmeals.remote;

import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilteredMealsAPIService {
    @GET("filter.php")
    Single<FilterResult> filterMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<FilterResult> filterMealsByIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Single<Meals> getMealById(@Query("i") String mealId);

    @GET("filter.php")
    Single<FilterResult> filterMealsByArea(@Query("a") String area);
}
