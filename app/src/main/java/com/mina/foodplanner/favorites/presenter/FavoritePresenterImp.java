package com.mina.foodplanner.favorites.presenter;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mina.foodplanner.data.FavoriteRepo;
import com.mina.foodplanner.data.model.Meal;
import java.util.List;

public class FavoritePresenterImp implements FavoritePresenter{

    FavoriteRepo favoriteRepo;

    public FavoritePresenterImp(Application application /*,FavoriteView favoriteView*/) {
        this.favoriteRepo = new FavoriteRepo(application);
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return favoriteRepo.getAllMeals();
    }

    @Override
    public void deleteMeal(Meal meal) {
        favoriteRepo.deleteMeal(meal);
    }

    @Override
    public void insertMeal(Meal meal) {
        favoriteRepo.insertMeal(meal);
    }

    @Override
    public void showMealDetails(Meal meal) {

    }
}
