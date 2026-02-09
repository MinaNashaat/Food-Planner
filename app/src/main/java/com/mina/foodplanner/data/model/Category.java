package com.mina.foodplanner.data.model;

import java.io.Serializable;

public class Category implements Serializable {
    String idCategory;
    String strCategory;
    String strCategoryDescription;
    String strCategoryThumb;

    public String getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }
}
