package com.mina.foodplanner.categorymeals.view;

import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface CategoryMealsView {
    void updateCategoryMealsList(List<FilteredMeal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
