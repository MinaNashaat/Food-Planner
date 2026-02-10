package com.mina.foodplanner.areameals.presenter;

public interface AreaMealsPresenter {
    void getAreaMeals(String area);
    void searchMeals(String query);
    void getMealByID(String mealID);
}
