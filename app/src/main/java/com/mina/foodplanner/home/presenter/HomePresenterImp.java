package com.mina.foodplanner.home.presenter;

import com.mina.foodplanner.data.HomeRepo;
//import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
//import com.mina.foodplanner.data.datasource.home.remote.RandomMealNetworkResponse;
import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.home.view.HomeView;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    HomeRepo homeRepo;
    HomeView homeView;
    private Meal dayMeal;
    CompositeDisposable disposables = new CompositeDisposable();
    CompositeDisposable disposables2 = new CompositeDisposable();
    public HomePresenterImp(HomeView homeView) {
        this.homeRepo = new HomeRepo();
        this.homeView = homeView;
    }

    @Override
    public void getDayMeal() {
        Disposable request = homeRepo.getDayMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    dayMeal = response.meals.get(0);
                    homeView.updateDayMeal(response.meals.get(0));
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        homeView.noInternetDayMeal();
                    } else {
                        homeView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);


//                new RandomMealNetworkResponse() {
//            @Override
//            public void onSuccess(Meal meal) {
//                dayMeal = meal;
//                homeView.updateDayMeal(meal);
//            }
//
//            @Override
//            public void noInternet() {
//                homeView.noInternetDayMeal();
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                homeView.onFailure(errorMessage);
//            }
//        });
    }


    @Override
    public void showDayMealDetails() {
        homeView.showMealDetails(dayMeal);
    }

    @Override
    public void getAllCategories() {
        Disposable request = homeRepo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    homeView.updateCategories(response.categories);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        homeView.noInternetCategories();
                    } else {
                        homeView.onFailureCategories("Conversion Error");
                    }
                });
        disposables2.add(request);


//                new CategoriesNetworkResponse() {
//            @Override
//            public void onSuccess(List<Category> categories) {
//                homeView.updateCategories(categories);
//            }
//
//            @Override
//            public void noInternet() {
//                homeView.noInternetCategories();
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                homeView.onFailureCategories(errorMessage);
//            }
//        });
    }

    @Override
    public void showAllCategories() {

    }

    @Override
    public void showCategoryMeals(Category category) {

    }

    @Override
    public void getAllCountries() {

    }

    @Override
    public void showAllCountries() {

    }

    @Override
    public void showAreaMeals(AreaString areaString) {

    }

    @Override
    public void onDestroy() {
        disposables.clear();
        disposables2.clear();
    }


}
