package com.mina.foodplanner.home.presenter;

import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;

public interface HomePresenter {
    void getDayMeal();
    void showMealDetails(Meal meal);

    void getAllCategories();
    void showAllCategories();
    void showCategoryMeals(Category category);

    void getAllCountries();
    void showAllCountries();
    void showAreaMeals(AreaString areaString);


}
