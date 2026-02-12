package com.mina.foodplanner.areameals.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.areameals.presenter.AreaMealsPresenter;
import com.mina.foodplanner.areameals.presenter.AreaMealsPresenterImp;
import com.mina.foodplanner.categorymeals.view.CategoryMealsAdapter;
import com.mina.foodplanner.data.model.FilteredMeal;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import org.checkerframework.checker.units.qual.Area;

import java.util.List;

public class AreaMealsActivity extends AppCompatActivity implements AreaMealsView, SpecificAreaMealView, OnAreaMealClick {

    AreaMealsPresenter presenter;
    AreaMealsAdapter adapter;
    EditText searchETAreaMealAct;
    RecyclerView favoritesRVAreaMealAct;
    ImageView btnBackAreaMealAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_meals);

        String area = getIntent().getStringExtra("area");
        presenter = new AreaMealsPresenterImp(this, this);
        presenter.getAreaMeals(area);
        searchETAreaMealAct = findViewById(R.id.searchETAreaMealAct);
        favoritesRVAreaMealAct = findViewById(R.id.favoritesRVAreaMealAct);
        btnBackAreaMealAct = findViewById(R.id.btnBackAreaMealAct);
        btnBackAreaMealAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        favoritesRVAreaMealAct.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AreaMealsAdapter();
        adapter.setOnFilteredMealClick(this);
        favoritesRVAreaMealAct.setAdapter(adapter);

        searchETAreaMealAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchMeals(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s,int a,int b,int c){}
            @Override public void afterTextChanged(Editable s){}
        });
    }

    @Override
    public void onAreaMealClick(FilteredMeal meal) {
        presenter.getMealByID(meal.getIdMeal());
    }

    @Override
    public void updateAreaMeals(List<FilteredMeal> meals) {
        adapter.setMealList(meals);
    }

    @Override
    public void openMealDetailsActivity(Meal meal) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra("meal", meal);
        startActivity(intent);
    }



    @Override public void noInternet() {}
    @Override public void onFailure(String errorMessage) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
