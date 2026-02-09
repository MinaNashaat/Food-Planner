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

@Dao
public interface UserPlannedMealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserPlannedMeal userPlannedMeal);

    @Delete
    void delete(UserPlannedMeal userPlannedMeal);

    @Query("SELECT * FROM user_planned_meals WHERE email = :email AND date = :date")
    List<UserPlannedMeal> getMealsForUserByDate(String email, String date);


    @Query("SELECT * FROM user_planned_meals WHERE email = :email")
    LiveData<List<UserPlannedMeal>> getAllUserPlannedMeals(String email);

}
