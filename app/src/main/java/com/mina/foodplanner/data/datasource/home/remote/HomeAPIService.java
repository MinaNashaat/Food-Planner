package com.mina.foodplanner.data.datasource.home.remote;

import com.mina.foodplanner.data.model.AllAreas;
import com.mina.foodplanner.data.model.AllCategories;
import com.mina.foodplanner.data.model.Categories;
import com.mina.foodplanner.data.model.Ingredients;
import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeAPIService {
    @GET("random.php")
    Single<Meals> getRandomMeal();  // i made this network response only

    @GET("categories.php")
    Single<Categories> getAllCategories();

    @GET("list.php?c=list")
    Call<AllCategories> getAllCategoriesString();




}
