package com.mina.foodplanner.recipedetails.presenter;

import android.util.Pair;

import com.mina.foodplanner.data.IngredientsRepo;
import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.model.Ingredient;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeDetailsPresenterImp implements RecipeDetailsPresenter{
    RecipeDetailsView view;

    public RecipeDetailsPresenterImp(RecipeDetailsView view) {
        this.view = view;
    }
    @Override
    public void loadMeal(Meal meal) {
        if (meal == null)
            return;

        view.showMeal(meal);
        List<Pair<String, String>> ingredients = extractIngredients(meal);
        view.showIngredients(ingredients);
        String videoId = extractYoutubeVideoId(meal.getStrYoutube());
        if (videoId != null) {
            view.playYoutubeVideo(videoId);
        }
    }

    private static String extractYoutubeVideoId(String url) {
        String videoId = null;

        String regex =
                "(?:youtube\\.com\\/(?:[^\\/]+\\/.*\\/|(?:v|e(?:mbed)?)\\/|.*[?&]v=)|youtu\\.be\\/)([^\"&?\\/\\s]{11})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            videoId = matcher.group(1);
        }

        return videoId;
    }

    private List<Pair<String, String>> extractIngredients(Meal meal) {
        List<Pair<String, String>> list = new ArrayList<>();

        addIfValid(list, meal.getStrIngredient1(), meal.getStrMeasure1());
        addIfValid(list, meal.getStrIngredient2(), meal.getStrMeasure2());
        addIfValid(list, meal.getStrIngredient3(), meal.getStrMeasure3());
        addIfValid(list, meal.getStrIngredient4(), meal.getStrMeasure4());
        addIfValid(list, meal.getStrIngredient5(), meal.getStrMeasure5());
        addIfValid(list, meal.getStrIngredient6(), meal.getStrMeasure6());
        addIfValid(list, meal.getStrIngredient7(), meal.getStrMeasure7());
        addIfValid(list, meal.getStrIngredient8(), meal.getStrMeasure8());
        addIfValid(list, meal.getStrIngredient9(), meal.getStrMeasure9());
        addIfValid(list, meal.getStrIngredient10(), meal.getStrMeasure10());
        addIfValid(list, meal.getStrIngredient11(), meal.getStrMeasure11());
        addIfValid(list, meal.getStrIngredient12(), meal.getStrMeasure12());
        addIfValid(list, meal.getStrIngredient13(), meal.getStrMeasure13());
        addIfValid(list, meal.getStrIngredient14(), meal.getStrMeasure14());
        addIfValid(list, meal.getStrIngredient15(), meal.getStrMeasure15());
        addIfValid(list, meal.getStrIngredient16(), meal.getStrMeasure16());
        addIfValid(list, meal.getStrIngredient17(), meal.getStrMeasure17());
        addIfValid(list, meal.getStrIngredient18(), meal.getStrMeasure18());
        addIfValid(list, meal.getStrIngredient19(), meal.getStrMeasure19());
        addIfValid(list, meal.getStrIngredient20(), meal.getStrMeasure20());

        return list;
    }

    private void addIfValid(List<Pair<String, String>> list, String ingredient, String measure) {

        if (ingredient == null || ingredient.isEmpty() || measure == null || measure.isEmpty()) {
            return;
        }

        list.add(new Pair<>(ingredient, measure));
    }

}
