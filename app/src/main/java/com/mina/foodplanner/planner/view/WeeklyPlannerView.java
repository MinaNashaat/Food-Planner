package com.mina.foodplanner.planner.view;

import com.mina.foodplanner.data.model.PlannerDay;
import com.mina.foodplanner.data.model.UserPlannedMeal;

import java.util.List;

public interface WeeklyPlannerView {
    void updateWeeklyPlannerMeals(List<UserPlannedMeal> userPlannedMeals);
    void getNextSevenDays(List<PlannerDay> days);
}
