package com.mina.foodplanner.allingredients.presenter;

import com.mina.foodplanner.allingredients.view.IngredientsView;
import com.mina.foodplanner.data.IngredientsRepo;
//import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.model.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientsPresenterImp implements IngredientsPresenter {

    IngredientsRepo ingredientsRepo;
    IngredientsView ingredientsView;
    CompositeDisposable disposables = new CompositeDisposable();

    private List<Ingredient> allIngredients;

    public IngredientsPresenterImp(IngredientsView ingredientsView) {
        this.ingredientsRepo = new IngredientsRepo();
        this.ingredientsView = ingredientsView;
    }

    @Override
    public void getAllIngredients() {
        Disposable request = ingredientsRepo.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    allIngredients = response.ingredients;
                    ingredientsView.updateIngredients(response.ingredients);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        ingredientsView.noInternet();
                    } else {
                        ingredientsView.onFailure("Conversion Error");
                    }
                });
        disposables.add(request);
    }

    @Override
    public void searchIngredients(String query) {
        if (allIngredients == null) return;

        if (query.isEmpty()) {
            ingredientsView.updateIngredients(allIngredients);
            return;
        }

        List<Ingredient> filtered = new ArrayList<>();

        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getStrIngredient()
                    .toLowerCase()
                    .contains(query.toLowerCase())) {
                filtered.add(ingredient);
            }
        }

        ingredientsView.updateIngredients(filtered);
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }
}