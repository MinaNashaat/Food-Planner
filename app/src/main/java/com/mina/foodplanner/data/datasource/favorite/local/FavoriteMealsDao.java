package com.mina.foodplanner.data.datasource.favorite.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

@Dao
public interface FavoriteMealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("select * from meals")
    LiveData<List<Meal>> getAllMeals();

    @Query("DELETE FROM meals")
    void deleteAll();
}
