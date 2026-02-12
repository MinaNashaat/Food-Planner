package com.mina.foodplanner.areameals.presenter;

import com.mina.foodplanner.areameals.view.AreaMealsView;
import com.mina.foodplanner.areameals.view.SpecificAreaMealView;
import com.mina.foodplanner.data.FilteredMealsRepo;
//import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
//import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AreaMealsPresenterImp implements AreaMealsPresenter {

    FilteredMealsRepo repo;
    AreaMealsView view;
    SpecificAreaMealView detailsView;
    CompositeDisposable disposables = new CompositeDisposable();
    CompositeDisposable disposables2 = new CompositeDisposable();

    private List<FilteredMeal> allMeals;

    public AreaMealsPresenterImp(AreaMealsView view,
                                 SpecificAreaMealView detailsView) {
        this.repo = new FilteredMealsRepo();
        this.view = view;
        this.detailsView = detailsView;
    }

    @Override
    public void getAreaMeals(String area) {
        Disposable request = repo.getAreaMeals(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    allMeals = response.filteredMeals;
                    view.updateAreaMeals(response.filteredMeals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        view.noInternet();
                    } else {
                        view.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);



//                , new FilteredMealsNetworkResponse() {
//            @Override
//            public void onSuccess(List<FilteredMeal> meals) {
//                allMeals = meals;
//                view.updateAreaMeals(meals);
//            }
//
//            @Override public void noInternet() {
//                view.noInternet();
//            }
//
//            @Override public void onFailure(String errorMessage) {
//                view.onFailure(errorMessage);
//            }
//        });
    }

    @Override
    public void searchMeals(String query) {
        if (allMeals == null) return;

        if (query == null || query.trim().isEmpty()) {
            view.updateAreaMeals(allMeals);
            return;
        }

        List<FilteredMeal> filtered = new ArrayList<>();
        for (FilteredMeal meal : allMeals) {
            if (meal.getStrMeal().toLowerCase()
                    .contains(query.toLowerCase())) {
                filtered.add(meal);
            }
        }
        view.updateAreaMeals(filtered);
    }

    @Override
    public void getMealByID(String mealID) {
        Disposable request = repo.getMealByID(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    detailsView.openMealDetailsActivity(response.meals.get(0));
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        detailsView.noInternet();
                    } else {
                        detailsView.onFailure("Conversion Error");
                    }
                });
        disposables2.add(request);

//                , new MealByIDNetworkResponse() {
//            @Override
//            public void onSuccess(List<Meal> meals) {
//                if (meals != null && !meals.isEmpty()) {
//                    detailsView.openMealDetailsActivity(meals.get(0));
//                }
//            }
//
//            @Override public void noInternet() {
//                detailsView.noInternet();
//            }
//
//            @Override public void onFailure(String errorMessage) {
//                detailsView.onFailure(errorMessage);
//            }
//        });
    }

    @Override
    public void onDestroy() {
        disposables.clear();
        disposables2.clear();
    }
}

