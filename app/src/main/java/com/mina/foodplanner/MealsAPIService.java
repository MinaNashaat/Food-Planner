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

    @GET("search.php")
    Call<Meals> searchMealsByName(@Query("s") String mealName);

    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") String mealId);

    @GET("random.php")
    Call<Meals> getRandomMeal();

    @GET("categories.php")
    Call<Categories> getAllCategories();

    @GET("list.php?c=list")
    Call<AllCategories> getAllCategoriesString();

    @GET("list.php?a=list")
    Call<AllAreas> getAllAreasString();

    @GET("list.php?i=list")
    Call<Ingredients> getAllIngredients();

    @GET("filter.php")
    Call<FilterResult> filterMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<FilterResult> filterMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<FilterResult> filterMealsByArea(@Query("a") String area);


}
