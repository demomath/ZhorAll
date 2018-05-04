package com.abc.zhor.model;


import android.databinding.ObservableField;

/**
 * Created by wudi on 2018/4/28.
 */

public class ZhorAppModel {

    private String main;
    private String home;
    private ObservableField<String> imageUrl;

    public ZhorAppModel(String main, String home, ObservableField<String> imageUrl) {
        this.main = main;
        this.home = home;
        this.imageUrl = imageUrl;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ObservableField<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
