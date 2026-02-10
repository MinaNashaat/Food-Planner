package com.mina.foodplanner.allingredients.view;

import com.mina.foodplanner.data.model.Ingredient;

import java.util.List;

public interface IngredientsView {
    void updateIngredients(List<Ingredient> ingredients);
    void noInternet();
    void onFailure(String errorMessage);
}
