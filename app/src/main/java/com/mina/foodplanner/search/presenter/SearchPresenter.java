package com.mina.foodplanner.search.presenter;

import com.mina.foodplanner.data.model.Meal;

public interface SearchPresenter {
    void searchByName(String mealName);
    void addToFavorite(Meal meal);
    void onDestroy();
}
