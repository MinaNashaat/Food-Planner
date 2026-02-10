package com.mina.foodplanner.areameals.view;

import com.mina.foodplanner.data.model.Meal;

public interface SpecificAreaMealView {
    void openMealDetailsActivity(Meal meal);
    void noInternet();
    void onFailure(String errorMessage);
}

