package com.example.bingimageproject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class People extends BaseObservable {
    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.bingimageproject.BR.name);
    }
}
