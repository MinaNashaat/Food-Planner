package com.mina.foodplanner.recipedetails.view;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.recipedetails.presenter.RecipeDetailsPresenter;
import com.mina.foodplanner.recipedetails.presenter.RecipeDetailsPresenterImp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsView{

    ImageView btnBackAct, favAct, recipeImageAct;
    TextView categoryNameAct, mealNameAct, stepsTVAct;
    Button addToPlanBtnAct;
    RecyclerView ingredientsRVAct;
    YouTubePlayerView youtubePlayerView;
    RecipeDetailsPresenter presenter;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        btnBackAct = findViewById(R.id.btnBackAct);
        favAct = findViewById(R.id.favAct);
        recipeImageAct = findViewById(R.id.recipeImageAct);
        categoryNameAct = findViewById(R.id.categoryNameAct);
        mealNameAct = findViewById(R.id.mealNameAct);
        stepsTVAct = findViewById(R.id.stepsTVAct);
        addToPlanBtnAct = findViewById(R.id.addToPlanBtnAct);
        ingredientsRVAct = findViewById(R.id.ingredientsRVAct);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);
        presenter = new RecipeDetailsPresenterImp(this,this);
        ingredientsAdapter = new IngredientsAdapter();
        ingredientsRVAct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ingredientsRVAct.setAdapter(ingredientsAdapter);
        Meal meal = (Meal) getIntent().getSerializableExtra("meal");

        presenter.loadMeal(meal);

        getLifecycle().addObserver(youtubePlayerView);

        addToPlanBtnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addToPlanner(meal,RecipeDetailsActivity.this);
            }
        });
    }

    @Override
    public void showMeal(Meal meal) {
        mealNameAct.setText(meal.getStrMeal());
        categoryNameAct.setText(meal.getStrCategory());
        stepsTVAct.setText(meal.getStrInstructions());
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(recipeImageAct);
    }

    @Override
    public void playYoutubeVideo(String videoId) {
        getLifecycle().addObserver(youtubePlayerView);

        youtubePlayerView.addYouTubePlayerListener(
                new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
    }

    @Override
    public void showIngredients(List<Pair<String, String>> ingredients) {
        ingredientsAdapter.setIngredientsList(ingredients);
    }
}