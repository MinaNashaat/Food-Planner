package com.mina.foodplanner.data.datasource.areas.remote;

import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
import com.mina.foodplanner.data.model.AllAreas;
import com.mina.foodplanner.data.model.AreaString;
import com.mina.foodplanner.data.model.Categories;
import com.mina.foodplanner.data.model.Category;
import com.mina.foodplanner.data.network.Network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaRemoteDataSource {
    AreaAPIService areaAPIService;

    public AreaRemoteDataSource() {
        this.areaAPIService = Network.getInstance().getAreaAPIService();
    }
    public void getAllAreas(AreasNetworkResponse callBack){

        Call<AllAreas> areas = areaAPIService.getAllAreasString();
        areas.enqueue(new Callback<AllAreas>() {
            @Override
            public void onResponse(Call<AllAreas> call, Response<AllAreas> response) {
                if(response.isSuccessful() && response.body()!= null){
                    List<AreaString> areaStringList = response.body().areaStringList;
                    callBack.onSuccess(areaStringList);
                }
                else{
                    callBack.onFailure("Error server");
                }
            }

            @Override
            public void onFailure(Call<AllAreas> call, Throwable t) {
                if(t instanceof IOException){
                    callBack.noInternet();
                }
                else{
                    callBack.onFailure("Conversion error");
                }
            }
        });

    }
}
