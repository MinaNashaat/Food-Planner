package com.mina.foodplanner.home.presenter;

import com.mina.foodplanner.data.HomeRepo;
import com.mina.foodplanner.data.datasource.home.remote.RandomMealNetworkResponse;
import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.home.view.HomeView;

public class HomePresenterImp implements HomePresenter{

    HomeRepo homeRepo;
    HomeView homeView;

    public HomePresenterImp(HomeView homeView) {
        this.homeRepo = new HomeRepo();
        this.homeView = homeView;
    }

    @Override
    public void getDayMeal() {
        homeRepo.getDayMeal(new RandomMealNetworkResponse() {
            @Override
            public void onSuccess(Meal meal) {
                homeView.updateDayMeal(meal);
            }

            @Override
            public void noInternet() {
                homeView.noInternet();
            }

            @Override
            public void onFailure(String errorMessage) {
                homeView.onFailure(errorMessage);
            }
        });
    }

    @Override
    public void showMealDetails(Meal meal) {

    }

    @Override
    public void getAllCategories() {

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
}
