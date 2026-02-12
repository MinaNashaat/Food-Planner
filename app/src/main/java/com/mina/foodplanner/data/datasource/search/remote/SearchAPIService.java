package com.mina.foodplanner.data.datasource.search.remote;

import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPIService {
    @GET("search.php")
    Single<Meals> searchMealsByName(@Query("s") String mealName);
}
