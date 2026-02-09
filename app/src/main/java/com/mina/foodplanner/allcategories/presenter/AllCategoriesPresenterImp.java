package com.mina.foodplanner.allcategories.presenter;

import com.mina.foodplanner.allcategories.view.AllCategoriesView;
import com.mina.foodplanner.data.HomeRepo;
import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
import com.mina.foodplanner.data.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesPresenterImp implements  AllCategoriesPresenter{

    HomeRepo homeRepo;
    AllCategoriesView allCategoriesView;
    private List<Category> allCategories;
    public AllCategoriesPresenterImp(AllCategoriesView allCategoriesView) {
        this.homeRepo = new HomeRepo();
        this.allCategoriesView = allCategoriesView;
    }
    @Override
    public void getAllCategories() {
        homeRepo.getAllCategories(new CategoriesNetworkResponse() {
            @Override
            public void onSuccess(List<Category> categories) {
                allCategories = categories;
                allCategoriesView.updateCategories(categories);
            }

            @Override
            public void noInternet() {
                allCategoriesView.noInternetCategories();
            }

            @Override
            public void onFailure(String errorMessage) {
                allCategoriesView.onFailureCategories(errorMessage);
            }
        });
    }

    @Override
    public void searchCategories(String query) {
        if (allCategories == null)
            return;

        if (query.isEmpty()) {
            allCategoriesView.updateCategories(allCategories);
            return;
        }

        List<Category> filteredList = new ArrayList<>();

        for (Category category : allCategories) {
            if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(category);
            }
        }

        allCategoriesView.updateCategories(filteredList);
    }
}
