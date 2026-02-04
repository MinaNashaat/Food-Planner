package com.mina.foodplanner.data.datasource.home.remote;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface RandomMealNetworkResponse {
    void onSuccess(Meal meal);
    void noInternet();
    void onFailure(String errorMessage);
}
