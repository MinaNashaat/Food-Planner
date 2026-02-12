package com.mina.foodplanner.data.datasource.favorite.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavoriteMealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(Meal meal);

    @Delete
    Completable  deleteMeal(Meal meal);

    @Query("select * from meals")
    Flowable<List<Meal>> getAllMeals();

    @Query("DELETE FROM meals")
    Completable  deleteAll();

    @Query("SELECT EXISTS(SELECT 1 FROM meals WHERE idMeal = :id)")
    Single<Boolean> isMealExists(String id);

}
