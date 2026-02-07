package com.mina.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.home.view.onMealClick;
import com.mina.foodplanner.search.presenter.SearchPresenter;
import com.mina.foodplanner.search.presenter.SearchPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView, onMealSelected {

    ImageView btnBack;
    EditText searchET;
    RecyclerView trendingRV;
    Button categoriesbTN, ingredientsBTN, areaBTN;
    SearchAdapter searchAdapter;
    SearchPresenter searchPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnBack = view.findViewById(R.id.btnBack);
        searchET = view.findViewById(R.id.searchET);
        trendingRV = view.findViewById(R.id.trendingRV);
        categoriesbTN = view.findViewById(R.id.categoriesbTN);
        ingredientsBTN = view.findViewById(R.id.ingredientsBTN);
        areaBTN = view.findViewById(R.id.areaBTN);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);

        trendingRV.setLayoutManager(gridLayoutManager);

        searchAdapter = new SearchAdapter();
        searchAdapter.setOnMealClick(this);
        searchPresenter = new SearchPresenterImp(this,view.getContext());
        trendingRV.setAdapter(searchAdapter);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                searchPresenter.searchByName(editable.toString());
                Log.d("minanashaat",editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }


    @Override
    public void updateMealSerachList(List<Meal> meals) {
        searchAdapter.setMealList(meals);
//        Log.d("minanashaat", "i am in fragment and first name is "+ meals.get(0).getStrMeal());
    }

    @Override
    public void noInternet() {

    }

    @Override
    public void onFailure(String errorMessage) {

    }


    @Override
    public void showMealDetails(Meal meal) {

    }

    @Override
    public void addToFav(Meal meal) {
        searchPresenter.addToFavorite(meal);
    }
}