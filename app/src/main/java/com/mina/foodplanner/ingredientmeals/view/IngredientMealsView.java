package com.mina.foodplanner.ingredientmeals.view;

import com.mina.foodplanner.data.model.FilteredMeal;

import java.util.List;

public interface IngredientMealsView {
    void updateCategoryMealsList(List<FilteredMeal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
