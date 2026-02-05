package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
import com.mina.foodplanner.data.datasource.home.remote.HomeRemoteDataSource;
import com.mina.foodplanner.data.datasource.home.remote.RandomMealNetworkResponse;

public class HomeRepo {

    HomeRemoteDataSource homeRemoteDataSource;

    public HomeRepo() {
        this.homeRemoteDataSource = new HomeRemoteDataSource();
    }

    public void getDayMeal(RandomMealNetworkResponse randomMealNetworkResponse){
        homeRemoteDataSource.getMealDay(randomMealNetworkResponse);
    }

    public void getAllCategories(CategoriesNetworkResponse callBack){
        homeRemoteDataSource.getAllCategories(callBack);
    }


}
