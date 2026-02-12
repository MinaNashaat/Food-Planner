package com.mina.foodplanner.data.datasource.userplannedmeals.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserPlannedMealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(UserPlannedMeal userPlannedMeal);

    @Delete
    Completable  delete(UserPlannedMeal userPlannedMeal);

    @Query("SELECT * FROM user_planned_meals WHERE email = :email AND date = :date")
    Single<List<UserPlannedMeal>> getMealsForUserByDate(String email, String date);


    @Query("SELECT * FROM user_planned_meals WHERE email = :email")
    Flowable<List<UserPlannedMeal>> getAllUserPlannedMeals(String email);

    @Query("DELETE FROM user_planned_meals")
    Completable  deleteAll();

    @Query("SELECT COUNT(*) FROM user_planned_meals WHERE idMeal = :id")
    Single<Integer> isMealExists(String id);

}
