package com.mina.foodplanner.allingredients.presenter;

import com.mina.foodplanner.allingredients.view.IngredientsView;
import com.mina.foodplanner.data.IngredientsRepo;
import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientsPresenterImp implements IngredientsPresenter {

    IngredientsRepo ingredientsRepo;
    IngredientsView ingredientsView;

    private List<Ingredient> allIngredients;

    public IngredientsPresenterImp(IngredientsView ingredientsView) {
        this.ingredientsRepo = new IngredientsRepo();
        this.ingredientsView = ingredientsView;
    }

    @Override
    public void getAllIngredients() {
        ingredientsRepo.getIngredients(new IngredientsNetworkResponse() {
            @Override
            public void onSuccess(List<Ingredient> ingredientsList) {
                allIngredients = ingredientsList;
                ingredientsView.updateIngredients(ingredientsList);
            }

            @Override
            public void onFailure(String error) {
                ingredientsView.onFailure(error);
            }
        });
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
}