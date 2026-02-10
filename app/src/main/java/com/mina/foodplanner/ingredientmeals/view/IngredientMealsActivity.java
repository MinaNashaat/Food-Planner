package com.mina.foodplanner.ingredientmeals.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.categorymeals.view.CategoryMealsAdapter;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Ingredient;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.ingredientmeals.presenter.IngredientMealsPresenter;
import com.mina.foodplanner.ingredientmeals.presenter.IngredientMealsPresenterImp;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import java.util.List;

public class IngredientMealsActivity extends AppCompatActivity implements IngredientMealsView, OnIngredientMealClick, SpecificIngredientMealView {

    EditText searchETIngMealAct;
    RecyclerView favoritesRVIngMealAct;
    IngredientMealsAdapter ingredientMealsAdapter;
    IngredientMealsPresenter ingredientMealsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_meals);
        searchETIngMealAct = findViewById(R.id.searchETIngMealAct);
        favoritesRVIngMealAct = findViewById(R.id.favoritesRVIngMealAct);
        ingredientMealsPresenter = new IngredientMealsPresenterImp(this,this);

        Ingredient ingredient = (Ingredient) getIntent().getSerializableExtra("ingredient");
        ingredientMealsPresenter.getIngredientMeals(ingredient.getStrIngredient());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        favoritesRVIngMealAct.setLayoutManager(linearLayoutManager);

        ingredientMealsAdapter = new IngredientMealsAdapter();
        ingredientMealsAdapter.setOnFilteredMealClick(this);
        favoritesRVIngMealAct.setAdapter(ingredientMealsAdapter);

        searchETIngMealAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ingredientMealsPresenter.searchMeals(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void updateCategoryMealsList(List<FilteredMeal> meals) {
        ingredientMealsAdapter.setMealList(meals);
    }

    @Override
    public void openMealDetailsActivity(List<Meal> meals) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("meal", meals.get(0));
        startActivity(intent);
    }

    @Override
    public void noInternet() {

    }

    @Override
    public void onFailure(String errorMessage) {

    }

    @Override
    public void onIngredientMealClick(FilteredMeal meal) {
        ingredientMealsPresenter.getMealByID(meal.getIdMeal());
    }
}