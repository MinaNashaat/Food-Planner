package com.mina.foodplanner.data;

//import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
import com.mina.foodplanner.data.datasource.home.remote.HomeRemoteDataSource;
//import com.mina.foodplanner.data.datasource.home.remote.RandomMealNetworkResponse;
import com.mina.foodplanner.data.model.Categories;
import com.mina.foodplanner.data.model.Meals;

import io.reactivex.rxjava3.core.Single;

public class HomeRepo {

    HomeRemoteDataSource homeRemoteDataSource;

    public HomeRepo() {
        this.homeRemoteDataSource = new HomeRemoteDataSource();
    }

    public Single<Meals> getDayMeal(){
        return homeRemoteDataSource.getMealDay();
    }

    public Single<Categories> getAllCategories(){
       return homeRemoteDataSource.getAllCategories();
    }


}
