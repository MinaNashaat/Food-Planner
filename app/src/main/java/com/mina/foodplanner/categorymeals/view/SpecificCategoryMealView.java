package com.mina.foodplanner.categorymeals.view;

import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface SpecificCategoryMealView {
    void openMealDetailsActivity(List<Meal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
