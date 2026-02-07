package com.mina.foodplanner.data;

import android.util.Log;

import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsRemoteDataSource;
import com.mina.foodplanner.data.model.Ingredient;

import java.util.List;

public class IngredientsRepo {
    private static IngredientsRepo instance;
    private IngredientsRemoteDataSource remoteDataSource;

    private static List<Ingredient> ingredients;

    private IngredientsRepo() {
        remoteDataSource = new IngredientsRemoteDataSource();
    }

    public static IngredientsRepo getInstance() {
        if (instance == null) {
            instance = new IngredientsRepo();
        }
        return instance;
    }

    public void getIngredients() {
        if (ingredients != null && !ingredients.isEmpty()) {
            return;
        }

        remoteDataSource.getAllIngredients(new IngredientsNetworkResponse() {
            @Override
            public void onSuccess(List<Ingredient> ingredientsList) {
                ingredients = ingredientsList;
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public Ingredient getIngredientByName(String name) {
        if (ingredients == null)
            return null;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getStrIngredient().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }
}
