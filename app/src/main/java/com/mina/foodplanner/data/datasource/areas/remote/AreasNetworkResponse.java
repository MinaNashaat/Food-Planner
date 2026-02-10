package com.mina.foodplanner.data.datasource.areas.remote;

import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Category;

import java.util.List;

public interface AreasNetworkResponse {
    void onSuccess(List<AreaString> areas);
    void noInternet();
    void onFailure(String errorMessage);
}
