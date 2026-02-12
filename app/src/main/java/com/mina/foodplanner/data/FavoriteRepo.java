package com.mina.foodplanner.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsLocalDataSource;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;


public class FavoriteRepo {

    FavoriteMealsLocalDataSource favoriteMealsLocalDataSource;

    public FavoriteRepo(Context context) {
        this.favoriteMealsLocalDataSource = new FavoriteMealsLocalDataSource(context);
    }

    public Completable insertMeal(Meal meal){
        return favoriteMealsLocalDataSource.insertMeal(meal);

    }
    public Completable deleteMeal(Meal meal){
        return favoriteMealsLocalDataSource.deleteMeal(meal);
    }

    public Flowable<List<Meal>> getAllMeals(){
        return favoriteMealsLocalDataSource.getAllMeals();
    }

    public Single<Boolean> isFavourite(String id){
        return favoriteMealsLocalDataSource.isMealExists(id);
    }



}
