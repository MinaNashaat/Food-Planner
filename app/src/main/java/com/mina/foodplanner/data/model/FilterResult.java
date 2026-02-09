package com.mina.foodplanner.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResult {
    @SerializedName("meals")
    public List<FilteredMeal> filteredMeals;
}
