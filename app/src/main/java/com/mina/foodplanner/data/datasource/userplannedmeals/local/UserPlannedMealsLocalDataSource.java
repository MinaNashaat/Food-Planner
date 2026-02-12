package com.mina.foodplanner.data.datasource.userplannedmeals.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

public class UserPlannedMealsLocalDataSource {

    private UserPlannedMealsDao userPlannedMealsDao;

    public UserPlannedMealsLocalDataSource(Context context) {
        userPlannedMealsDao = AppDatabase.getInstance(context).userPlannedMealsDao();
    }

    public void insertUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        userPlannedMealsDao.insert(userPlannedMeal);
    }

    public void deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        userPlannedMealsDao.delete(userPlannedMeal);
    }

    public List<UserPlannedMeal> getMealsForUserByDate(String email, String date) {
        return userPlannedMealsDao.getMealsForUserByDate(email, date);
    }

    public LiveData<List<UserPlannedMeal>> getAllUserPlannedMeals(String email) {
        return userPlannedMealsDao.getAllUserPlannedMeals(email);
    }

    public int isFavourite(String id){
        return userPlannedMealsDao.isMealExists(id);
    }

}