package com.mina.foodplanner.ingredientmeals.presenter;

public interface IngredientMealsPresenter {
    void getIngredientMeals(String ingredient);
    void searchMeals(String query);
    void getMealByID(String mealID);
}
