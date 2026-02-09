package com.mina.foodplanner.planner.presenter;

import androidx.lifecycle.LiveData;

import com.mina.foodplanner.data.model.PlannerDay;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

public interface PlannerPresenter {
    LiveData<List<UserPlannedMeal>> getAllUserPlannedMeals();
    void deleteUserPlannedMeal(UserPlannedMeal userPlannedMeal);
    void updateMealsBasedonDay(PlannerDay plannerDay);
    void generateNext7Days();
}
