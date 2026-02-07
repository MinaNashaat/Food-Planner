package com.mina.foodplanner.search.presenter;



import android.content.Context;
import android.util.Log;

import com.mina.foodplanner.data.SearchReo;
import com.mina.foodplanner.data.datasource.search.remote.SearchByNameNetworkResponse;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.search.view.SearchView;

import java.util.List;

public class SearchPresenterImp implements SearchPresenter{

    SearchReo searchReo;
    SearchView searchView;

    public SearchPresenterImp(SearchView searchView, Context context) {
        this.searchReo = new SearchReo(context);
        this.searchView = searchView;
    }

    @Override
    public void searchByName(String mealName) {
        searchReo.searchMealByName(mealName, new SearchByNameNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> meals) {
                searchView.updateMealSerachList(meals);
//                Log.d("minanashaat","The size of search = " + meals.size());
            }

            @Override
            public void noInternet() {
                searchView.noInternet();
            }

            @Override
            public void onFailure(String errorMessage) {
                searchView.onFailure(errorMessage);
            }

            @Override
            public void nothingMatchTheSearch(String message) {
                searchView.onFailure(message);
            }
        });
    }

    @Override
    public void addToFavorite(Meal meal) {
        searchReo.addToFavorite(meal);
    }
}
