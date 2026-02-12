package com.mina.foodplanner.allingredients.view;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.allingredients.presenter.IngredientsPresenter;
import com.mina.foodplanner.allingredients.presenter.IngredientsPresenterImp;
import com.mina.foodplanner.data.model.Ingredient;
import com.mina.foodplanner.ingredientmeals.presenter.IngredientMealsPresenter;
import com.mina.foodplanner.ingredientmeals.view.IngredientMealsActivity;

import java.util.List;

public class AllIngredientsActivity extends AppCompatActivity implements IngredientsView, OnIngredientClick {

    EditText searchETIngAct;
    RecyclerView favoritesRVIngAct;
    IngredientsAdapter ingredientsAdapter;
    IngredientsPresenter presenter;
    ImageView btnBackIngAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ingredients);

        searchETIngAct = findViewById(R.id.searchETIngAct);
        favoritesRVIngAct = findViewById(R.id.favoritesRVIngAct);
        btnBackIngAct = findViewById(R.id.btnBackIngAct);
        btnBackIngAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        favoritesRVIngAct.setLayoutManager(gridLayoutManager);

        ingredientsAdapter = new IngredientsAdapter();
        ingredientsAdapter.setOnIngredientClick(this);
        favoritesRVIngAct.setAdapter(ingredientsAdapter);

        presenter = new IngredientsPresenterImp(this);

        presenter.getAllIngredients();

        searchETIngAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchIngredients(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

    }

    @Override
    public void updateIngredients(List<Ingredient> ingredients) {
        ingredientsAdapter.setIngredientList(ingredients);
    }

    @Override
    public void noInternet() {

    }

    @Override
    public void onFailure(String errorMessage) {

    }

    @Override
    public void onIngredientSelected(Ingredient ingredient) {
        Intent intent = new Intent(this, IngredientMealsActivity.class);
        intent.putExtra("ingredient", ingredient);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}