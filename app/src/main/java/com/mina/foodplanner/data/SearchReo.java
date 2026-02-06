package com.mina.foodplanner.data;

import com.mina.foodplanner.data.datasource.search.remote.SearchByNameNetworkResponse;
import com.mina.foodplanner.data.datasource.search.remote.SearchRemoteDataSource;

public class SearchReo {
    SearchRemoteDataSource searchRemoteDataSource;

    public SearchReo() {
        this.searchRemoteDataSource = new SearchRemoteDataSource();
    }

    public void searchMealByName(String name, SearchByNameNetworkResponse callBack){
        searchRemoteDataSource.searchMealsByName(name, callBack);
    }
}
