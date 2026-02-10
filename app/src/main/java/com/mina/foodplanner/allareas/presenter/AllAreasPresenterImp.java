package com.mina.foodplanner.allareas.presenter;

import com.mina.foodplanner.allareas.view.AllAreasView;
import com.mina.foodplanner.data.AreaRepo;
import com.mina.foodplanner.data.datasource.areas.remote.AreasNetworkResponse;
import com.mina.foodplanner.data.model.AreaString;

import java.util.ArrayList;
import java.util.List;

public class AllAreasPresenterImp implements AllAreasPresenter{
    AreaRepo areaRepo;
    AllAreasView allAreasView;
    private List<AreaString> areaStringList;
    public AllAreasPresenterImp(AllAreasView allAreasView) {
        areaRepo = new AreaRepo();
        this.allAreasView = allAreasView;
    }

    @Override
    public void getAllAreas() {
        areaRepo.getAllAreas(new AreasNetworkResponse() {
            @Override
            public void onSuccess(List<AreaString> areas) {
                areaStringList = areas;
                allAreasView.updateAreas(areas);
            }

            @Override
            public void noInternet() {
                allAreasView.noInternetAreas();
            }

            @Override
            public void onFailure(String errorMessage) {
                allAreasView.onFailureAreas(errorMessage);
            }
        });
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
}
