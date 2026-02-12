package com.mina.foodplanner.allcategories.presenter;

public interface AllCategoriesPresenter {
    void getAllCategories();
    void searchCategories(String query);
    void onDestroy();
}
