package com.mina.foodplanner.favorites.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mina.foodplanner.R;
import com.mina.foodplanner.data.FavoriteRepo;
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.favorites.presenter.FavoritePresenter;
import com.mina.foodplanner.favorites.presenter.FavoritePresenterImp;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouriteFragment extends Fragment implements OnFavoriteClickListener {

    RecyclerView favoritesRV;
    FavoriteAdapter favoriteAdapter;
    FavoritePresenter favoritePresenter;
    CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesRV = view.findViewById(R.id.favoritesRV);
        favoritePresenter = new FavoritePresenterImp(getActivity().getApplication());
        favoriteAdapter = new FavoriteAdapter(this);
        compositeDisposable = new CompositeDisposable();
        favoritesRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        favoritesRV.setAdapter(favoriteAdapter);



        favoritePresenter.getAllMeals();

//        favoritePresenter.getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                favoriteAdapter.updateMealsList(meals);
//            }
//        });

        compositeDisposable.add(
                favoritePresenter.getAllMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(meals -> {
                            favoriteAdapter.updateMealsList(meals);
                        })
        );


    }

    @Override
    public void onClick(Meal meal) {
        favoritePresenter.deleteMeal(meal);
    }

    @Override
    public void showMealDetails(Meal meal) {
        Intent intent = new Intent(requireActivity(), RecipeDetailsActivity.class);
        intent.putExtra("meal", meal);
        startActivity(intent);
    }
}