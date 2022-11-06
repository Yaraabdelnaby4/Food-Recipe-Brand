package com.example.foodrecipebrand.models;

public class Categories {
    private  String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    public Categories(String idCategory, String strCategory, String strCategoryThumb) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
    }

    public Categories(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    public Categories(String idCategory, String strCategory) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
    }


    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }
}
