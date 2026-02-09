package com.mina.foodplanner.data.datasource.categorymeals.remote;

import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface MealByIDNetworkResponse {
    void onSuccess(List<Meal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
