package com.mina.foodplanner.planner.view;

import com.mina.foodplanner.data.model.Meal;
import com.mina.foodplanner.data.model.UserPlannedMeal;

public interface onUserPlannedMealClick {
    void onMealImageClick(Meal meal);
    void onRemoveIconClick(UserPlannedMeal userPlannedMeal);

}
