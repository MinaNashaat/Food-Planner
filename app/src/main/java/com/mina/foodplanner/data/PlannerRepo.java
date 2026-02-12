package com.mina.foodplanner.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.datasource.userplannedmeals.local.UserPlannedMealsLocalDataSource;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class PlannerRepo {
    UserPlannedMealsLocalDataSource userPlannedMealsLocalDataSource;

    public PlannerRepo(Context context) {
        this.userPlannedMealsLocalDataSource = new UserPlannedMealsLocalDataSource(context);
    }
    public Completable  insertUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
       return userPlannedMealsLocalDataSource.insertUserPlannedMeal(userPlannedMeal);
    }

    public Completable deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal) {
        return userPlannedMealsLocalDataSource.deleteUserPlannedMeal(userPlannedMeal);
    }

    public Single<List<UserPlannedMeal>> getMealsForUserByDate(String email, String date) {
        return userPlannedMealsLocalDataSource.getMealsForUserByDate(email, date);
    }

    public Flowable<List<UserPlannedMeal>> getAllUserPlannedMeals(String email) {
        return userPlannedMealsLocalDataSource.getAllUserPlannedMeals(email);
    }

    public Single<Integer> isFavourite(String id){
        return userPlannedMealsLocalDataSource.isFavourite(id);
    }

}
