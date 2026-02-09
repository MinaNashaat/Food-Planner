package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.categorymeals.remote.CategoriesMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.categorymeals.remote.CategoryMealsRemoteDataSource;
import com.mina.foodplanner.data.datasource.categorymeals.remote.MealByIDNetworkResponse;

public class CategoryMealsRepo {
    CategoryMealsRemoteDataSource categoryMealsRemoteDataSource;

    public CategoryMealsRepo() {
        this.categoryMealsRemoteDataSource = new CategoryMealsRemoteDataSource();
    }

    public void getCategoryMeals(String categoryMealName, CategoriesMealsNetworkResponse callBack){
        categoryMealsRemoteDataSource.getCategoryMeals(categoryMealName, callBack);
    }

    public void getMealByID(String mealID, MealByIDNetworkResponse callBack){
        categoryMealsRemoteDataSource.getMealById(mealID,callBack);
    }

}
