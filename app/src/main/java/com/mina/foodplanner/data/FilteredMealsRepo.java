package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsRemoteDataSource;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;

public class FilteredMealsRepo {
    FilteredMealsRemoteDataSource categoryMealsRemoteDataSource;

    public FilteredMealsRepo() {
        this.categoryMealsRemoteDataSource = new FilteredMealsRemoteDataSource();
    }

    public void getCategoryMeals(String categoryMealName, FilteredMealsNetworkResponse callBack){
        categoryMealsRemoteDataSource.getCategoryMeals(categoryMealName, callBack);
    }

    public void getMealByID(String mealID, MealByIDNetworkResponse callBack){
        categoryMealsRemoteDataSource.getMealById(mealID,callBack);
    }

    public void getIngredientsMeals(String ingredientMealName, FilteredMealsNetworkResponse callBack){
        categoryMealsRemoteDataSource.getIngredientMeals(ingredientMealName,callBack);
    }

}
