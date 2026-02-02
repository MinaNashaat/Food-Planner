package com.mina.foodplanner;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        String uID = getIntent().getStringExtra("uid");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");

        Log.d("minanashaat","ID = "+uID);
        Log.d("minanashaat","email = "+email);
        Log.d("minanashaat","name = "+name);

    }
}