package com.mina.foodplanner.areameals.view;

import com.mina.foodplanner.data.model.FilteredMeal;

import java.util.List;

public interface AreaMealsView {
    void updateAreaMeals(List<FilteredMeal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}

