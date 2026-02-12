package com.mina.foodplanner.allcategories.presenter;

import com.mina.foodplanner.allcategories.view.AllCategoriesView;
import com.mina.foodplanner.data.HomeRepo;
//import com.mina.foodplanner.data.datasource.home.remote.CategoriesNetworkResponse;
import com.mina.foodplanner.data.model.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllCategoriesPresenterImp implements  AllCategoriesPresenter{

    HomeRepo homeRepo;
    AllCategoriesView allCategoriesView;
    private List<Category> allCategories;
    CompositeDisposable disposables = new CompositeDisposable();
    public AllCategoriesPresenterImp(AllCategoriesView allCategoriesView) {
        this.homeRepo = new HomeRepo();
        this.allCategoriesView = allCategoriesView;
    }
    @Override
    public void getAllCategories() {
        Disposable request = homeRepo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    allCategories = response.categories;
                    allCategoriesView.updateCategories(response.categories);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        allCategoriesView.noInternetCategories();
                    } else {
                        allCategoriesView.onFailureCategories("Conversion Error");
                    }
                });
        disposables.add(request);

//                new CategoriesNetworkResponse() {
//            @Override
//            public void onSuccess(List<Category> categories) {
//                allCategories = categories;
//                allCategoriesView.updateCategories(categories);
//            }
//
//            @Override
//            public void noInternet() {
//                allCategoriesView.noInternetCategories();
//            }
//
//            @Override
//            public void onFailure(String errorMessage) {
//                allCategoriesView.onFailureCategories(errorMessage);
//            }
//        });
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

    @Override
    public void onDestroy() {
        disposables.clear();
    }
}
