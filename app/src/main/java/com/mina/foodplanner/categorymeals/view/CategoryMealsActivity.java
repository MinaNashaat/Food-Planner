package com.mina.foodplanner.categorymeals.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.categorymeals.presenter.CategoryMealsPresenter;
import com.mina.foodplanner.categorymeals.presenter.CategoryMealsPresenterImp;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import java.util.List;

public class CategoryMealsActivity extends AppCompatActivity implements OnCategoryMealClick, CategoryMealsView, SpecificCategoryMealView{

    EditText searchETCatMealAct;
    RecyclerView favoritesRVCatMealAct;
    CategoryMealsPresenter categoryMealsPresenter;
    CategoryMealsAdapter categoryMealsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_meals);


        Category category = (Category) getIntent().getSerializableExtra("category");
        searchETCatMealAct = findViewById(R.id.searchETCatMealAct);
        favoritesRVCatMealAct = findViewById(R.id.favoritesRVCatMealAct);

        Log.d("minanashaat" ,"Cat name = "+ category.getStrCategory());

        categoryMealsPresenter = new CategoryMealsPresenterImp(this,this);
        categoryMealsPresenter.getCategoryMeals(category.getStrCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        favoritesRVCatMealAct.setLayoutManager(linearLayoutManager);

        categoryMealsAdapter = new CategoryMealsAdapter();
        categoryMealsAdapter.setOnFilteredMealClick(this);
        favoritesRVCatMealAct.setAdapter(categoryMealsAdapter);

        searchETCatMealAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                categoryMealsPresenter.searchMeals(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    @Override
    public void onCategoryMealClick(FilteredMeal meal) {
        categoryMealsPresenter.getMealByID(meal.getIdMeal());
    }

    @Override
    public void updateCategoryMealsList(List<FilteredMeal> meals) {
        categoryMealsAdapter.setMealList(meals);
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
}