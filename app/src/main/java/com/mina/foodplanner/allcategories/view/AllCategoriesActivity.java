package com.mina.foodplanner.allcategories.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.allcategories.presenter.AllCategoriesPresenter;
import com.mina.foodplanner.allcategories.presenter.AllCategoriesPresenterImp;
import com.mina.foodplanner.categorymeals.view.CategoryMealsActivity;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity implements AllCategoriesView, onCategoryClick{

    ImageView btnBackCatAct;
    EditText searchETCatAct;
    RecyclerView favoritesRVCatAct;
    CategoriesAdapter categoriesAdapter;
    AllCategoriesPresenter allCategoriesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        btnBackCatAct = findViewById(R.id.btnBackCatAct);
        searchETCatAct = findViewById(R.id.searchETCatAct);
        favoritesRVCatAct = findViewById(R.id.favoritesRVCatAct);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        favoritesRVCatAct.setLayoutManager(gridLayoutManager);
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setOnCategoryClick(this);
        favoritesRVCatAct.setAdapter(categoriesAdapter);

        allCategoriesPresenter = new AllCategoriesPresenterImp(this);
        allCategoriesPresenter.getAllCategories();

        searchETCatAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                allCategoriesPresenter.searchCategories(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    @Override
    public void updateCategories(List<Category> categories) {
        categoriesAdapter.setCategoryList(categories);
    }

    @Override
    public void noInternetCategories() {

    }

    @Override
    public void onFailureCategories(String errorMessage) {

    }

    @Override
    public void onCategorySelected(Category category) {
        Intent intent = new Intent(this, CategoryMealsActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}