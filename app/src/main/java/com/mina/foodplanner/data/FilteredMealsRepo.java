package com.mina.foodplanner.data;

//import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsRemoteDataSource;
//import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilterResult;
import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;

public class FilteredMealsRepo {
    FilteredMealsRemoteDataSource categoryMealsRemoteDataSource;

    public FilteredMealsRepo() {
        this.categoryMealsRemoteDataSource = new FilteredMealsRemoteDataSource();
    }

    public Single<FilterResult> getCategoryMeals(String categoryMealName){
       return categoryMealsRemoteDataSource.getCategoryMeals(categoryMealName);
    }

    public Single<Meals> getMealByID(String mealID){
        return categoryMealsRemoteDataSource.getMealById(mealID);
    }

    public Single<FilterResult> getIngredientsMeals(String ingredientMealName){
        return categoryMealsRemoteDataSource.getIngredientMeals(ingredientMealName);
    }

    public Single<FilterResult> getAreaMeals(String area){
        return categoryMealsRemoteDataSource.getAreaMeals(area);
    }


}
