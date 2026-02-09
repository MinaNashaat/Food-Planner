package com.mina.foodplanner.data.datasource.categorymeals.remote;

import com.mina.foodplanner.data.model.FilteredMeal;

import java.util.List;

public interface CategoriesMealsNetworkResponse {
    void onSuccess(List<FilteredMeal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
