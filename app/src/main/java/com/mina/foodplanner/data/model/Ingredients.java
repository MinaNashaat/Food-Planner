package com.mina.foodplanner.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredients {

    @SerializedName("meals")
    public List<Ingredient> ingredients;
}
