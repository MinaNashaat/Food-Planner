package com.mina.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Meal;

public class HomeFragment extends Fragment implements onMealClick {

    View mealOfDay;
    View categoriesSection;
    View countriesSection;

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
        ImageView mealImage = mealOfDay.findViewById(R.id.mealDayImage);
        TextView mealTitle = mealOfDay.findViewById(R.id.mealDayName);
        TextView mealCategory = mealOfDay.findViewById(R.id.mealDayCategory);
        TextView seeAllCategories = categoriesSection.findViewById(R.id.seeAllCategories);
        RecyclerView allCategoriesRV = categoriesSection.findViewById(R.id.allCategoriesRV);

        TextView seeAllCountries = countriesSection.findViewById(R.id.seeAllCountries);
        RecyclerView allCountriesRV = countriesSection.findViewById(R.id.allCountriesRV);



    }

    @Override
    public void showMealDetails(Meal meal) {

    }

}