package com.mina.foodplanner.data.datasource.favorite.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class FavoriteMealsLocalDataSource {
    Context context;
    FavoriteMealsDao favoriteMealsDao ;

    public FavoriteMealsLocalDataSource(Context context) {
        this.context = context;
        favoriteMealsDao = AppDatabase.getInstance(context).mealsDao();
    }

    public Completable insertMeal(Meal meal){
        return favoriteMealsDao.insertMeal(meal);

    }
    public Completable deleteMeal(Meal meal){
        return favoriteMealsDao.deleteMeal(meal);
    }

    public Flowable<List<Meal>> getAllMeals(){
        return favoriteMealsDao.getAllMeals();
    }

    public Single<Boolean> isMealExists(String id){
        return favoriteMealsDao.isMealExists(id);
    }
}
