package com.mina.foodplanner.data.datasource.areas.remote;

import com.mina.foodplanner.data.model.AllAreas;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AreaAPIService {
    @GET("list.php?a=list")
    Single<AllAreas> getAllAreasString();
}
