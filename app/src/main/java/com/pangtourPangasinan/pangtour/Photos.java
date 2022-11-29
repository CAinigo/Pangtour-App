package com.pangtourPangasinan.pangtour;

public class Photos extends Places {

    private String image ;
    private String aPhoto;

    public Photos(){

    }


    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
        this.aPhoto = aPhoto;
    }

    public Photos(String image) {
        this.image = image;
    }


}
