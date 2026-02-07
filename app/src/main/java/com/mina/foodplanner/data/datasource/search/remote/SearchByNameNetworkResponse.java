package com.mina.foodplanner.data.datasource.search.remote;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface SearchByNameNetworkResponse {
    void onSuccess(List<Meal> meal);
    void noInternet();
    void onFailure(String errorMessage);
}
