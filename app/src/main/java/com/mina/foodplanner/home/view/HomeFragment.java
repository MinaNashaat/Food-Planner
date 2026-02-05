package com.mina.foodplanner.home.view;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.home.presenter.HomePresenter;
import com.mina.foodplanner.home.presenter.HomePresenterImp;

import java.util.List;

public class HomeFragment extends Fragment implements onMealClick, HomeView, onCategoryClick {

    View mealOfDay;
    View categoriesSection;
    View countriesSection;
    HomePresenter homePresenter;
    ImageView mealImage;
    TextView mealTitle;
    TextView mealCategory;
    TextView seeAllCategories;
    RecyclerView allCategoriesRV;
    TextView seeAllCountries;
    RecyclerView allCountriesRV;
    CategoriesAdapter categoriesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealOfDay = view.findViewById(R.id.mealOfDayLayout);
        categoriesSection = view.findViewById(R.id.categoriesSection);
        countriesSection = view.findViewById(R.id.countriesSection);
        mealImage = mealOfDay.findViewById(R.id.mealDayImage);
        mealTitle = mealOfDay.findViewById(R.id.mealDayName);
        mealCategory = mealOfDay.findViewById(R.id.mealDayCategory);
        seeAllCategories = categoriesSection.findViewById(R.id.seeAllCategories);
        allCategoriesRV = categoriesSection.findViewById(R.id.allCategoriesRV);
        seeAllCountries =  countriesSection.findViewById(R.id.seeAllCountries);
        allCountriesRV = countriesSection.findViewById(R.id.allCountriesRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        allCategoriesRV.setLayoutManager(linearLayoutManager);
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setOnCategoryClick(this);
        allCategoriesRV.setAdapter(categoriesAdapter);

        homePresenter = new HomePresenterImp(this);
        homePresenter.getDayMeal();
        homePresenter.getAllCategories();

    }

    @Override
    public void showMealDetails(Meal meal) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void updateDayMeal(Meal meal) {
        mealTitle.setText(meal.getStrMeal());
        mealCategory.setText(meal.getStrCategory());
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(mealImage);
    }

    @Override
    public void noInternetDayMeal() {

    }

    @Override
    public void onFailure(String errorMessage) {

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

    }
}