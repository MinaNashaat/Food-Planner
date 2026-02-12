package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.areas.remote.AreaRemoteDataSource;
//import com.mina.foodplanner.data.datasource.areas.remote.AreasNetworkResponse;
import com.mina.foodplanner.data.model.AllAreas;

import io.reactivex.rxjava3.core.Single;

public class AreaRepo {
    AreaRemoteDataSource areaRemoteDataSource;

    public AreaRepo() {
        this.areaRemoteDataSource = new AreaRemoteDataSource();
    }

    public Single<AllAreas> getAllAreas(){
        return areaRemoteDataSource.getAllAreas();
    }
}
