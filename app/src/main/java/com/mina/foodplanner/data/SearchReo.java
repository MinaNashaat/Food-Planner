package com.mina.foodplanner.data;

import android.content.Context;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsLocalDataSource;
import com.mina.foodplanner.data.datasource.search.remote.SearchByNameNetworkResponse;
import com.mina.foodplanner.data.datasource.search.remote.SearchRemoteDataSource;
import com.mina.foodplanner.data.model.Meal;

public class SearchReo {
    SearchRemoteDataSource searchRemoteDataSource;
    FavoriteMealsLocalDataSource favoriteMealsLocalDataSource;

    public SearchReo(Context context) {
        this.searchRemoteDataSource = new SearchRemoteDataSource();
        this.favoriteMealsLocalDataSource = new FavoriteMealsLocalDataSource(context);
    }

    public void searchMealByName(String name, SearchByNameNetworkResponse callBack){
        searchRemoteDataSource.searchMealsByName(name, callBack);
    }

    public void addToFavorite(Meal meal){
        favoriteMealsLocalDataSource.insertMeal(meal);
    }
}
