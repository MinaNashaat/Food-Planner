package com.mina.foodplanner.allareas.presenter;

import android.util.Log;

import com.mina.foodplanner.allareas.view.AllAreasView;
import com.mina.foodplanner.data.AreaRepo;
//import com.mina.foodplanner.data.datasource.areas.remote.AreasNetworkResponse;
import com.mina.foodplanner.data.model.AreaString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllAreasPresenterImp implements AllAreasPresenter {
    AreaRepo areaRepo;
    AllAreasView allAreasView;
    private List<AreaString> areaStringList;
    CompositeDisposable disposables = new CompositeDisposable();

    public AllAreasPresenterImp(AllAreasView allAreasView) {
        areaRepo = new AreaRepo();
        this.allAreasView = allAreasView;
    }

    @Override
    public void getAllAreas() {

        Disposable request = areaRepo.getAllAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
//                    Log.d("TAG", "getAllAreas: ");
                    areaStringList = response.areaStringList;
                    allAreasView.updateAreas(response.areaStringList);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        allAreasView.noInternetAreas();
                    } else {
                        allAreasView.onFailureAreas("Conversion Error");
                    }
                });
        disposables.add(request);

//        areaRepo.getAllAreas(new AreasNetworkResponse() {
//            @Override
//            public void onSuccess(List<AreaString> areas) {
//                areaStringList = areas;
//                allAreasView.updateAreas(areas);
//            }
//
//            @Override
//            public void noInternet() {
//                allAreasView.noInternetAreas();
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                allAreasView.onFailureAreas(errorMessage);
//            }
//        });
    }

    @Override
    public void searchAreas(String query) {
        if (areaStringList == null)
            return;

        if (query.isEmpty()) {
            allAreasView.updateAreas(areaStringList);
            return;
        }

        List<AreaString> filteredList = new ArrayList<>();

        for (AreaString area : areaStringList) {
            if (area.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(area);
            }
        }

        allAreasView.updateAreas(filteredList);
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }
}
