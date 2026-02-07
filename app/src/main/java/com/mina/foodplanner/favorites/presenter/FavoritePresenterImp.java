package com.mina.foodplanner.favorites.presenter;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.FavoriteRepo;
import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsLocalDataSource;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.favorites.view.FavoriteView;

import java.util.List;

public class FavoritePresenterImp implements FavoritePresenter{

    FavoriteMealsLocalDataSource favoriteMealsLocalDataSource;
//    FavoriteView favoriteView;
    FavoriteRepo favoriteRepo;

    public FavoritePresenterImp(Application application /*,FavoriteView favoriteView*/) {
        this.favoriteMealsLocalDataSource = new FavoriteMealsLocalDataSource(application);
//        this.favoriteView= favoriteView;
        this.favoriteRepo = new FavoriteRepo(application);
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return favoriteMealsLocalDataSource.getAllMeals();
    }

    @Override
    public void deleteMeal(Meal meal) {
        favoriteMealsLocalDataSource.deleteMeal(meal);
    }

    @Override
    public void insertMeal(Meal meal) {
        favoriteMealsLocalDataSource.insertMeal(meal);
    }

    @Override
    public void showMealDetails(Meal meal) {

    }
}
