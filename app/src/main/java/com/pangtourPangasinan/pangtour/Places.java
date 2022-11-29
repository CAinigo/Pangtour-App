package com.pangtourPangasinan.pangtour;

import java.io.Serializable;

public class Places implements Serializable {
    private String pImage, otherImage_1, otherImage_2;
    private String pName, categories;
    private String pDescription, pLocation;


    public Places(){

    }


    public Places(String pImage, String pName) {
        this.pImage = pImage;
        this.pName = pName;
        this.categories= categories;
        this.pDescription= pDescription;
        this.pLocation= pLocation;
        this.otherImage_1= otherImage_1;
        this.otherImage_2= otherImage_2;
    }


    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpDescription() {
        return pDescription;
    }


    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpName() {
        return pName;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    public String getpLocation() {
        return pLocation;
    }

    public void setotherImage_1(String otherImage_1) {
        this.otherImage_1 = otherImage_1;
    }

    public String getotherImage_1() {
        return otherImage_1;

    }

    public String getotherImage_2() {
        return otherImage_2;
    }

    public String getcategories() {
        return categories;
    }



}
