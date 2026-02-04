package com.mina.foodplanner.home.view;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void updateDayMeal(Meal meal);
    void noInternet();
    void onFailure(String errorMessage);
}
