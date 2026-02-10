package com.mina.foodplanner.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllAreas {
    @SerializedName("meals")
    public List<AreaString> areaStringList;
}
