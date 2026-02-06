package com.mina.foodplanner.search.view;

import com.mina.foodplanner.data.model.Meal;

public interface onMealSelected {
    void showMealDetails(Meal meal);
    void addToFav(Meal meal);

}
