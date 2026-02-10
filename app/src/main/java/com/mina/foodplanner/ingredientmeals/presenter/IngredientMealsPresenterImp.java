package com.mina.foodplanner.ingredientmeals.presenter;

import com.mina.foodplanner.data.FilteredMealsRepo;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.ingredientmeals.view.IngredientMealsView;
import com.mina.foodplanner.ingredientmeals.view.SpecificIngredientMealView;

import java.util.ArrayList;
import java.util.List;

public class IngredientMealsPresenterImp implements IngredientMealsPresenter{

    FilteredMealsRepo categoryMealsRepo;
    IngredientMealsView ingredientMealsView;
    SpecificIngredientMealView specificIngredientMealView;
    private List<FilteredMeal> allMeals;
    public IngredientMealsPresenterImp(IngredientMealsView ingredientMealsView, SpecificIngredientMealView specificIngredientMealView) {
        this.categoryMealsRepo = new FilteredMealsRepo();
        this.ingredientMealsView = ingredientMealsView;
        this.specificIngredientMealView = specificIngredientMealView;
    }

    @Override
    public void getIngredientMeals(String ingredient) {
        categoryMealsRepo.getIngredientsMeals(ingredient, new FilteredMealsNetworkResponse() {
            @Override
            public void onSuccess(List<FilteredMeal> meals) {
                allMeals = meals;
                ingredientMealsView.updateCategoryMealsList(meals);
            }

            @Override
            public void noInternet() {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }

    @Override
    public void searchMeals(String query) {
        if (allMeals == null) return;

        if (query == null || query.trim().isEmpty()) {
            ingredientMealsView.updateCategoryMealsList(allMeals);
            return;
        }

        List<FilteredMeal> filtered = new ArrayList<>();

        for (FilteredMeal meal : allMeals) {
            if (meal.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(meal);
            }
        }

        ingredientMealsView.updateCategoryMealsList(filtered);
    }

    @Override
    public void getMealByID(String mealID) {
        categoryMealsRepo.getMealByID(mealID, new MealByIDNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> meals) {
                specificIngredientMealView.openMealDetailsActivity(meals);
            }

            @Override
            public void noInternet() {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
