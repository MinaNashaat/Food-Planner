package com.mina.foodplanner.categorymeals.presenter;

import com.mina.foodplanner.categorymeals.view.CategoryMealsView;
import com.mina.foodplanner.categorymeals.view.SpecificCategoryMealView;
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

public class CategoryMealsPresenterImp implements CategoryMealsPresenter {
    FilteredMealsRepo categoryMealsRepo;
    CategoryMealsView categoryMealsView;
    SpecificCategoryMealView specificCategoryMealView;
    CompositeDisposable disposables = new CompositeDisposable();
    CompositeDisposable disposables2 = new CompositeDisposable();

    private List<FilteredMeal> allMeals;
    public CategoryMealsPresenterImp(CategoryMealsView categoryMealsView, SpecificCategoryMealView specificCategoryMealView) {
        this.categoryMealsRepo = new FilteredMealsRepo();
        this.categoryMealsView = categoryMealsView;
        this.specificCategoryMealView = specificCategoryMealView;
    }

    @Override
    public void getCategoryMeals(String category) {
        Disposable request = categoryMealsRepo.getCategoryMeals(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    categoryMealsView.updateCategoryMealsList(response.filteredMeals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        categoryMealsView.noInternet();
                    } else {
                        categoryMealsView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);
//        categoryMealsRepo.getCategoryMeals(category, new FilteredMealsNetworkResponse() {
//            @Override
//            public void onSuccess(List<FilteredMeal> meals) {
//                allMeals = meals;
//                categoryMealsView.updateCategoryMealsList(meals);
//
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
            categoryMealsView.updateCategoryMealsList(allMeals);
            return;
        }

        List<FilteredMeal> filtered = new ArrayList<>();

        for (FilteredMeal meal : allMeals) {
            if (meal.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(meal);
            }
        }

        categoryMealsView.updateCategoryMealsList(filtered);
    }

    @Override
    public void getMealByID(String mealID){
        Disposable request = categoryMealsRepo.getMealByID(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    specificCategoryMealView.openMealDetailsActivity(response.meals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        specificCategoryMealView.noInternet();
                    } else {
                        specificCategoryMealView.onFailure("Conversion Error");
                    }
                });
        disposables2.add(request);



//                , new MealByIDNetworkResponse() {
//            @Override
//            public void onSuccess(List<Meal> meals) {
//                specificCategoryMealView.openMealDetailsActivity(meals);
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
    public void onDestroy() {
        disposables.clear();
        disposables2.clear();
    }

}
