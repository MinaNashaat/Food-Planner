package com.mina.foodplanner.search.presenter;



import android.content.Context;
import android.util.Log;

import com.mina.foodplanner.data.SearchReo;
//import com.mina.foodplanner.data.datasource.search.remote.SearchByNameNetworkResponse;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.search.view.SearchView;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter{

    SearchReo searchReo;
    SearchView searchView;
    CompositeDisposable disposables = new CompositeDisposable();

    public SearchPresenterImp(SearchView searchView, Context context) {
        this.searchReo = new SearchReo(context);
        this.searchView = searchView;
    }

    @Override
    public void searchByName(String mealName) {
        Disposable request = searchReo.searchMealByName(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    searchView.updateMealSerachList(response.meals);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        searchView.noInternet();
                    } else {
                        searchView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);




//                , new SearchByNameNetworkResponse() {
//            @Override
//            public void onSuccess(List<Meal> meals) {
//                searchView.updateMealSerachList(meals);
////                Log.d("minanashaat","The size of search = " + meals.size());
//            }
//
//            @Override
//            public void noInternet() {
//                searchView.noInternet();
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                searchView.onFailure(errorMessage);
//            }
//
//        });
    }

    @Override
    public void addToFavorite(Meal meal) {
        searchReo.addToFavorite(meal);
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }
}
