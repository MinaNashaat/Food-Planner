package com.mina.foodplanner.planner.presenter;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.model.PlannerDay;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlannerPresenter {
    Flowable<List<UserPlannedMeal>> getAllUserPlannedMeals();
    void deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal);
    void updateMealsBasedonDay(PlannerDay plannerDay);
    void generateNext7Days();
    void onDestroy();
}
