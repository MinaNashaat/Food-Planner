package com.mina.foodplanner.allareas.view;

import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Category;

import java.util.List;

public interface AllAreasView {
    void updateAreas(List<AreaString> categories);
    void noInternetAreas();
    void onFailureAreas(String errorMessage);
}
