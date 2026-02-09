package com.mina.foodplanner.categorymeals.presenter;

import com.mina.foodplanner.categorymeals.view.CategoryMealsView;
import com.mina.foodplanner.categorymeals.view.SpecificCategoryMealView;
import com.mina.foodplanner.data.CategoryMealsRepo;
import com.mina.foodplanner.data.datasource.categorymeals.remote.CategoriesMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.categorymeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CategoryMealsPresenterImp implements CategoryMealsPresenter {
    CategoryMealsRepo categoryMealsRepo;
    CategoryMealsView categoryMealsView;
    SpecificCategoryMealView specificCategoryMealView;
    private List<FilteredMeal> allMeals;
    public CategoryMealsPresenterImp(CategoryMealsView categoryMealsView, SpecificCategoryMealView specificCategoryMealView) {
        this.categoryMealsRepo = new CategoryMealsRepo();
        this.categoryMealsView = categoryMealsView;
        this.specificCategoryMealView = specificCategoryMealView;
    }

    @Override
    public void getCategoryMeals(String category) {
        categoryMealsRepo.getCategoryMeals(category, new CategoriesMealsNetworkResponse() {
            @Override
            public void onSuccess(List<FilteredMeal> meals) {
                allMeals = meals;
                categoryMealsView.updateCategoryMealsList(meals);

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
            categoryMealsView.updateCategoryMealsList(allMeals);
            return;
        }

        List<FilteredMeal> filtered = new ArrayList<>();

        for (FilteredMeal meal : allMeals) {
            if (meal.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(meal);
            }
        }

        categoryMealsView.updateCategoryMealsList(filtered);
    }

    @Override
    public void getMealByID(String mealID){
        categoryMealsRepo.getMealByID(mealID, new MealByIDNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> meals) {
                specificCategoryMealView.openMealDetailsActivity(meals);
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
