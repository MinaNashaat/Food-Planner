package com.mina.foodplanner.areameals.presenter;

import com.mina.foodplanner.areameals.view.AreaMealsView;
import com.mina.foodplanner.areameals.view.SpecificAreaMealView;
import com.mina.foodplanner.data.FilteredMealsRepo;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.FilteredMealsNetworkResponse;
import com.mina.foodplanner.data.datasource.filteredmeals.remote.MealByIDNetworkResponse;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AreaMealsPresenterImp implements AreaMealsPresenter {

    FilteredMealsRepo repo;
    AreaMealsView view;
    SpecificAreaMealView detailsView;

    private List<FilteredMeal> allMeals;

    public AreaMealsPresenterImp(AreaMealsView view,
                                 SpecificAreaMealView detailsView) {
        this.repo = new FilteredMealsRepo();
        this.view = view;
        this.detailsView = detailsView;
    }

    @Override
    public void getAreaMeals(String area) {
        repo.getAreaMeals(area, new FilteredMealsNetworkResponse() {
            @Override
            public void onSuccess(List<FilteredMeal> meals) {
                allMeals = meals;
                view.updateAreaMeals(meals);
            }

            @Override public void noInternet() {
                view.noInternet();
            }

            @Override public void onFailure(String errorMessage) {
                view.onFailure(errorMessage);
            }
        });
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
        repo.getMealByID(mealID, new MealByIDNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    detailsView.openMealDetailsActivity(meals.get(0));
                }
            }

            @Override public void noInternet() {
                detailsView.noInternet();
            }

            @Override public void onFailure(String errorMessage) {
                detailsView.onFailure(errorMessage);
            }
        });
    }
}

