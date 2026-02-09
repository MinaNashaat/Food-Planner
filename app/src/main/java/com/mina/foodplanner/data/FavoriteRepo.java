package com.mina.foodplanner.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsLocalDataSource;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;


public class FavoriteRepo {

    FavoriteMealsLocalDataSource favoriteMealsLocalDataSource;

    public FavoriteRepo(Application application) {
        this.favoriteMealsLocalDataSource = new FavoriteMealsLocalDataSource(application);
    }

    public void insertMeal(Meal meal){
        favoriteMealsLocalDataSource.insertMeal(meal);

    }
    public void deleteMeal(Meal meal){
        favoriteMealsLocalDataSource.deleteMeal(meal);
    }

    public LiveData<List<Meal>> getAllMeals(){
        return favoriteMealsLocalDataSource.getAllMeals();
    }


}
