package com.mina.foodplanner.data.datasource.favorite.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public class FavoriteMealsLocalDataSource {
    Context context;
    FavoriteMealsDao favoriteMealsDao ;

    public FavoriteMealsLocalDataSource(Context context) {
        this.context = context;
        favoriteMealsDao = AppDatabase.getInstance(context).mealsDao();
    }

    public void insertMeal(Meal meal){
        favoriteMealsDao.insertMeal(meal);

    }
    public void deleteMeal(Meal meal){
        favoriteMealsDao.deleteMeal(meal);
    }

    public LiveData<List<Meal>> getAllMeals(){
        return favoriteMealsDao.getAllMeals();
    }
}
