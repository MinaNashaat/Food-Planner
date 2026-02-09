package com.mina.foodplanner.recipedetails.presenter;

import android.content.Context;

import com.mina.foodplanner.data.model.Meal;

public interface RecipeDetailsPresenter {
    void loadMeal(Meal meal);
    void addToPlanner(Meal meal, Context context);
}
