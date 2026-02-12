package com.mina.foodplanner.data;

import android.content.Context;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsLocalDataSource;
//import com.mina.foodplanner.data.datasource.search.remote.SearchByNameNetworkResponse;
import com.mina.foodplanner.data.datasource.search.remote.SearchRemoteDataSource;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;

public class SearchReo {
    SearchRemoteDataSource searchRemoteDataSource;
    FavoriteMealsLocalDataSource favoriteMealsLocalDataSource;

    public SearchReo(Context context) {
        this.searchRemoteDataSource = new SearchRemoteDataSource();
        this.favoriteMealsLocalDataSource = new FavoriteMealsLocalDataSource(context);
    }

    public Single<Meals> searchMealByName(String name){
        return searchRemoteDataSource.searchMealsByName(name);
    }

    public void addToFavorite(Meal meal){
        favoriteMealsLocalDataSource.insertMeal(meal);
    }
}
