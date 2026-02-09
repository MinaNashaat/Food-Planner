package com.mina.foodplanner.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsDao;
import com.mina.foodplanner.data.datasource.userplannedmeals.local.UserPlannedMealsDao;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;

@Database(entities = {Meal.class, UserPlannedMeal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteMealsDao mealsDao();
    public abstract UserPlannedMealsDao userPlannedMealsDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, "mealsDB")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
