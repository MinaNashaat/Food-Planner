package com.mina.foodplanner.planner.view;

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
import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.PlannerDay;
import com.mina.foodplanner.data.model.UserPlannedMeal;
import com.mina.foodplanner.planner.presenter.PlannerPresenter;
import com.mina.foodplanner.planner.presenter.PlannerPresenterImp;
import com.mina.foodplanner.recipedetails.view.RecipeDetailsActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeeklyPlannerFragment extends Fragment implements WeeklyPlannerView, onUserPlannedMealClick, OnDayClick{

    RecyclerView mealsRV, daysRV;
    WeeklyPlannerAdapter weeklyPlannerAdapter;
    DaysAdapter daysAdapter;
    PlannerPresenter plannerPresenter;
    CompositeDisposable disposable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsRV = view.findViewById(R.id.mealsRV);
        daysRV = view.findViewById(R.id.daysRV);
        disposable = new CompositeDisposable();
        plannerPresenter = new PlannerPresenterImp(view.getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        mealsRV.setLayoutManager(linearLayoutManager);
        weeklyPlannerAdapter = new WeeklyPlannerAdapter();
        weeklyPlannerAdapter.setOnUserPlannedMealClick(this);
        mealsRV.setAdapter(weeklyPlannerAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(view.getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        daysRV.setLayoutManager(linearLayoutManager2);
        daysAdapter = new DaysAdapter();
        daysAdapter.setOnDayClick(this);
        daysRV.setAdapter(daysAdapter);

        plannerPresenter.generateNext7Days();

        disposable.add(
                plannerPresenter.getAllUserPlannedMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> weeklyPlannerAdapter.setUserPlannedMealsList(meals)
                        )
        );

//        plannerPresenter.getAllUserPlannedMeals().observe(getViewLifecycleOwner(), new Observer<List<UserPlannedMeal>>() {
//            @Override
//            public void onChanged(List<UserPlannedMeal> userPlannedMeals) {
//                weeklyPlannerAdapter.setUserPlannedMealsList(userPlannedMeals);
//            }
//        });

    }

    @Override
    public void updateWeeklyPlannerMeals(List<UserPlannedMeal> userPlannedMeals) {
        weeklyPlannerAdapter.setUserPlannedMealsList(userPlannedMeals);
    }

    @Override
    public void getNextSevenDays(List<PlannerDay> days) {
        daysAdapter.setDaysList(days);
    }

    @Override
    public void onMealImageClick(Meal meal) {
        Intent intent = new Intent(requireActivity(), RecipeDetailsActivity.class);
        intent.putExtra("meal", meal);
        startActivity(intent);
    }

    @Override
    public void onRemoveIconClick(UserPlannedMeal userPlannedMeal) {
        plannerPresenter.deleteUserPlannedMeal(userPlannedMeal);
    }

    @Override
    public void onDayClick(PlannerDay day) {
        plannerPresenter.updateMealsBasedonDay(day);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.clear();
        plannerPresenter.onDestroy();
    }

}