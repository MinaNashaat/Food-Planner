package com.mina.foodplanner.ingredientmeals.presenter;

import com.mina.foodplanner.data.FilteredMealsRepo;
//import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
//import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.ingredientmeals.view.IngredientMealsView;
import com.mina.foodplanner.ingredientmeals.view.SpecificIngredientMealView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientMealsPresenterImp implements IngredientMealsPresenter{

    FilteredMealsRepo categoryMealsRepo;
    IngredientMealsView ingredientMealsView;
    SpecificIngredientMealView specificIngredientMealView;
    private List<FilteredMeal> allMeals;
    CompositeDisposable disposables = new CompositeDisposable();
    CompositeDisposable disposables2 = new CompositeDisposable();

    public IngredientMealsPresenterImp(IngredientMealsView ingredientMealsView, SpecificIngredientMealView specificIngredientMealView) {
        this.categoryMealsRepo = new FilteredMealsRepo();
        this.ingredientMealsView = ingredientMealsView;
        this.specificIngredientMealView = specificIngredientMealView;
    }

    @Override
    public void getIngredientMeals(String ingredient) {
        Disposable request = categoryMealsRepo.getIngredientsMeals(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    allMeals = response.filteredMeals;
                    ingredientMealsView.updateCategoryMealsList(response.filteredMeals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        ingredientMealsView.noInternet();
                    } else {
                        ingredientMealsView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);

//                , new FilteredMealsNetworkResponse() {
//            @Override
//            public void onSuccess(List<FilteredMeal> meals) {
//                allMeals = meals;
//                ingredientMealsView.updateCategoryMealsList(meals);
//            }
//
//            @Override
//            public void noInternet() {
//
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//
//            }
//        });
    }

    @Override
    public void searchMeals(String query) {
        if (allMeals == null) return;

        if (query == null || query.trim().isEmpty()) {
            ingredientMealsView.updateCategoryMealsList(allMeals);
            return;
        }

        List<FilteredMeal> filtered = new ArrayList<>();

        for (FilteredMeal meal : allMeals) {
            if (meal.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(meal);
            }
        }

        ingredientMealsView.updateCategoryMealsList(filtered);
    }

    @Override
    public void getMealByID(String mealID) {
        Disposable request = categoryMealsRepo.getMealByID(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    specificIngredientMealView.openMealDetailsActivity(response.meals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        specificIngredientMealView.noInternet();
                    } else {
                        specificIngredientMealView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);

//                , new MealByIDNetworkResponse() {
//            @Override
//            public void onSuccess(List<Meal> meals) {
//                specificIngredientMealView.openMealDetailsActivity(meals);
//            }
//
//            @Override
//            public void noInternet() {
//
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//
//            }
//        });
    }
}
