package com.mina.foodplanner.categorymeals.presenter;

public interface CategoryMealsPresenter  {
    void getCategoryMeals(String category);
    void searchMeals(String query);
    void getMealByID(String mealID);
}
