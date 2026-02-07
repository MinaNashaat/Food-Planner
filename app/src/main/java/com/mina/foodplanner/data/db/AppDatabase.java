package com.mina.foodplanner.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mina.foodplanner.data.datasource.favorite.local.FavoriteMealsDao;
import com.mina.foodplanner.data.model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteMealsDao mealsDao();
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
