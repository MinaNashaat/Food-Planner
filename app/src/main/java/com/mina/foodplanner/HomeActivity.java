package com.mina.foodplanner;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mina.foodplanner.data.IngredientsRepo;
//import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsNetworkResponse;
import com.mina.foodplanner.data.datasource.ingredients.remote.IngredientsRemoteDataSource;
import com.mina.foodplanner.data.model.Ingredient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView2);

        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomNav, navController);

        String uID = getIntent().getStringExtra("uid");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");

        Log.d("minanashaat","ID = " + uID);
        Log.d("minanashaat","email = " + email);
        Log.d("minanashaat","name = " + name);

        IngredientsRepo.getInstance().getIngredients();

        IngredientsRepo.getInstance()
                .getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredients -> {
                    IngredientsRepo.ingredients = ingredients.ingredients;
                });


    }
}