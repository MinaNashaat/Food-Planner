package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.areas.remote.AreaRemoteDataSource;
import com.mina.foodplanner.data.datasource.areas.remote.AreasNetworkResponse;

public class AreaRepo {
    AreaRemoteDataSource areaRemoteDataSource;

    public AreaRepo() {
        this.areaRemoteDataSource = new AreaRemoteDataSource();
    }

    public void getAllAreas(AreasNetworkResponse callBack){
        areaRemoteDataSource.getAllAreas(callBack);
    }
}
