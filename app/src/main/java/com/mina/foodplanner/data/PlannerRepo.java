package com.mina.foodplanner.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.datasource.userplannedmeals.local.UserPlannedMealsLocalDataSource;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

public class PlannerRepo {
    UserPlannedMealsLocalDataSource userPlannedMealsLocalDataSource;

    public PlannerRepo(Context context) {
        this.userPlannedMealsLocalDataSource = new UserPlannedMealsLocalDataSource(context);
    }
    public void insertUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        userPlannedMealsLocalDataSource.insertUserPlannedMeal(userPlannedMeal);
    }

    public void deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        userPlannedMealsLocalDataSource.deleteUserPlannedMeal(userPlannedMeal);
    }

    public List<UserPlannedMeal> getMealsForUserByDate(String email, String date) {
        return userPlannedMealsLocalDataSource.getMealsForUserByDate(email, date);
    }

    public LiveData<List<UserPlannedMeal>> getAllUserPlannedMeals(String email) {
        return userPlannedMealsLocalDataSource.getAllUserPlannedMeals(email);
    }

    public int isFavourite(String id){
        return userPlannedMealsLocalDataSource.isFavourite(id);
    }

}
