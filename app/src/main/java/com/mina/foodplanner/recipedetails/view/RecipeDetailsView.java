package com.mina.foodplanner.recipedetails.view;

import android.util.Pair;

import com.mina.foodplanner.data.model.Meal;

import java.util.List;

public interface RecipeDetailsView {
    void showMeal(Meal meal);
    void playYoutubeVideo(String videoId);
    void showIngredients(List<Pair<String, String>> ingredients);
    void showOrHideFavourite(boolean isFavourite);
}

