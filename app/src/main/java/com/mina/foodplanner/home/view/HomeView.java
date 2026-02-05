package com.mina.foodplanner.home.view;

import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void updateDayMeal(Meal meal);
    void noInternetDayMeal();
    void onFailure(String errorMessage);

    void updateCategories(List<Category> categories);
    void noInternetCategories();
    void onFailureCategories(String errorMessage);
}
