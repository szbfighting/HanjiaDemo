package com.example.restaurantreviews.model;

public class Dishes {

    private String imgAdress;
    private String name;

    public Dishes(String imgAdress, String name) {
        this.imgAdress = imgAdress;
        this.name = name;
    }

    public String getImgAdress() {
        return imgAdress;
    }

    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
