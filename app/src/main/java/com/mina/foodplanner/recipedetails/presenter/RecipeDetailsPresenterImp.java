package com.mina.foodplanner.recipedetails.presenter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.mina.foodplanner.data.FavoriteRepo;
import com.mina.foodplanner.data.IngredientsRepo;
import com.mina.foodplanner.data.PlannerRepo;
import com.mina.foodplanner.data.SharedPrefrencesRepo;
//import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.model.Ingredient;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsPresenterImp implements RecipeDetailsPresenter {
    RecipeDetailsView view;
    PlannerRepo plannerRepo;
    FavoriteRepo favoriteRepo;
    SharedPrefrencesRepo sharedPrefrencesRepo;
    CompositeDisposable disposable;
    public RecipeDetailsPresenterImp(RecipeDetailsView view, Context context) {
        this.view = view;
        plannerRepo = new PlannerRepo(context);
        sharedPrefrencesRepo = new SharedPrefrencesRepo(context);
        disposable = new CompositeDisposable();
        favoriteRepo = new FavoriteRepo(context);
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

    @Override
    public void addToPlanner(Meal meal, Context context) {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year, month, dayOfMonth) -> {

                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year, month, dayOfMonth);

                    String selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            .format(selectedCalendar.getTime());

                    String email = sharedPrefrencesRepo.getUserEmail();

                    UserPlannedMeal plannedMeal =
                            new UserPlannedMeal(email, selectedDate, meal);

                    disposable.add(
                            plannerRepo.insertUserPlannedMeal(plannedMeal)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                    )
                    );
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.getDatePicker()
                .setMinDate(System.currentTimeMillis());

        datePickerDialog.show();
    }


    @Override
    public void isFavourite(Meal meal) {

        disposable.add(
                favoriteRepo.isFavourite(meal.getIdMeal())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::showOrHideFavourite,
                                Throwable::printStackTrace
                        )
        );
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
