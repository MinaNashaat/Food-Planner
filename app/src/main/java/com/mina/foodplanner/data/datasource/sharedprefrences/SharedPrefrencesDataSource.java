package com.mina.foodplanner.data.datasource.sharedprefrences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrencesDataSource {

    private static final String PREF_NAME = "user_session";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOGGED_IN = "logged_in";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SharedPrefrencesDataSource(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUser(String email, String name) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }

    public String getName() {
        return prefs.getString(KEY_NAME, null);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
    public String getUserName() {
        return prefs.getString("name", null);
    }

    public String getUserEmail() {
        return prefs.getString("email", null);
    }

}
