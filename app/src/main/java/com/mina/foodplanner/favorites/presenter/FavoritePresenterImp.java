package com.mina.foodplanner.favorites.presenter;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mina.foodplanner.data.FavoriteRepo;
import com.mina.foodplanner.data.model.Meal;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoritePresenter{

    FavoriteRepo favoriteRepo;
    CompositeDisposable compositeDisposable;
    public FavoritePresenterImp(Application application /*,FavoriteView favoriteView*/) {
        this.favoriteRepo = new FavoriteRepo(application);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public Flowable<List<Meal>> getAllMeals() {
        return favoriteRepo.getAllMeals();
    }

    @Override
    public void deleteMeal(Meal meal) {
        compositeDisposable.add(
                favoriteRepo.deleteMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    @Override
    public void insertMeal(Meal meal) {
        compositeDisposable.add(
                favoriteRepo.insertMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    @Override
    public void showMealDetails(Meal meal) {

    }



    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
