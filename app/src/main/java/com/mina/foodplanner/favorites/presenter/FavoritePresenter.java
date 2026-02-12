package com.mina.foodplanner.favorites.presenter;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoritePresenter {
    Flowable<List<Meal>> getAllMeals();
    void deleteMeal(Meal meal);
    void insertMeal(Meal meal);
    void showMealDetails(Meal meal);
//    void isFavourite(Meal meal);
    void onDestroy();
}
