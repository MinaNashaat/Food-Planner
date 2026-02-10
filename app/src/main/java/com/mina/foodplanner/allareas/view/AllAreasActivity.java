package com.mina.foodplanner.allareas.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.foodplanner.R;
import com.mina.foodplanner.allareas.presenter.AllAreasPresenter;
import com.mina.foodplanner.allareas.presenter.AllAreasPresenterImp;
import com.mina.foodplanner.areameals.view.AreaMealsActivity;
import com.mina.foodplanner.data.model.AreaString;

import java.util.List;

public class AllAreasActivity extends AppCompatActivity implements AllAreasView, OnAreaClick {

    EditText searchETAreaAct;
    RecyclerView favoritesRVAreaAct;
    AreasAdapter adapter;
    AllAreasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_areas);

        searchETAreaAct = findViewById(R.id.searchETAreaAct);
        favoritesRVAreaAct = findViewById(R.id.favoritesRVAreaAct);

        favoritesRVAreaAct.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AreasAdapter();
        adapter.setOnAreaClick(this);
        favoritesRVAreaAct.setAdapter(adapter);

        presenter = new AllAreasPresenterImp(this);
        presenter.getAllAreas();

        searchETAreaAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchAreas(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s,int a,int b,int c){}
            @Override public void afterTextChanged(Editable s){}
        });
    }

    @Override
    public void updateAreas(List<AreaString> areas) {
        adapter.setAreaList(areas);
    }

    @Override
    public void onAreaClick(String area) {
        Intent intent = new Intent(this, AreaMealsActivity.class);
        intent.putExtra("area", area);
        startActivity(intent);
    }

    @Override public void noInternetAreas() {}
    @Override public void onFailureAreas(String errorMessage) {}
}
