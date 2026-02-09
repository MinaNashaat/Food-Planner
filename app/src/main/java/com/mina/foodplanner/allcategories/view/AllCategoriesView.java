package com.mina.foodplanner.allcategories.view;

import com.mina.foodplanner.data.model.Category;

import java.util.List;

public interface AllCategoriesView {
    void updateCategories(List<Category> categories);
    void noInternetCategories();
    void onFailureCategories(String errorMessage);
}
