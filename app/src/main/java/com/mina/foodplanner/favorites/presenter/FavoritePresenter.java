package com.mina.foodplanner.favorites.presenter;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface FavoritePresenter {
    LiveData<List<Meal>> getAllMeals();
    void deleteMeal(Meal meal);
    void insertMeal(Meal meal);
    void showMealDetails(Meal meal);
}
