package com.mina.foodplanner.search.view;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface SearchView {
    void updateMealSerachList(List<Meal> meals);
    void noInternet();
    void onFailure(String errorMessage);
}
