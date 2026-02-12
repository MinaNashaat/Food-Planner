package com.mina.foodplanner.data.datasource.userplannedmeals.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.db.AppDatabase;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class UserPlannedMealsLocalDataSource {

    private UserPlannedMealsDao userPlannedMealsDao;

    public UserPlannedMealsLocalDataSource(Context context) {
        userPlannedMealsDao = AppDatabase.getInstance(context).userPlannedMealsDao();
    }

    public Completable  insertUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        return userPlannedMealsDao.insert(userPlannedMeal);
    }

    public Completable deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        return userPlannedMealsDao.delete(userPlannedMeal);
    }

    public Single<List<UserPlannedMeal>> getMealsForUserByDate(String email, String date) {
        return userPlannedMealsDao.getMealsForUserByDate(email, date);
    }

    public Flowable<List<UserPlannedMeal>> getAllUserPlannedMeals(String email) {
        return userPlannedMealsDao.getAllUserPlannedMeals(email);
    }

    public Single<Integer> isFavourite(String id){
        return userPlannedMealsDao.isMealExists(id);
    }

}