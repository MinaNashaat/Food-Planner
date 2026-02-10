package com.mina.foodplanner.ingredientmeals.view;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface SpecificIngredientMealView {
    void openMealDetailsActivity(List<Meal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
