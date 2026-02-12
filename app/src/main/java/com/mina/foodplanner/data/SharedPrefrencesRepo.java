package com.mina.foodplanner.data;

import android.content.Context;

import com.mina.foodplanner.data.datasource.sharedprefrences.SharedPrefrencesDataSource;

public class SharedPrefrencesRepo {
    private final SharedPrefrencesDataSource dataSource;

    public SharedPrefrencesRepo(Context context) {
        dataSource = new SharedPrefrencesDataSource(context);
    }

    public void saveUser(String email, String name) {
        dataSource.saveUser(email, name);
    }

    public boolean isLoggedIn() {
        return dataSource.isLoggedIn();
    }

    public String getUserEmail() {
        return dataSource.getEmail();
    }

    public String getUserName() {
        return dataSource.getName();
    }

    public void logout() {
        dataSource.logout();
    }
}
