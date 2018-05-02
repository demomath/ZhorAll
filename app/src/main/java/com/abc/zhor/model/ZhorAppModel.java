package com.abc.zhor.model;



/**
 * Created by wudi on 2018/4/28.
 */

public class ZhorAppModel {

    private String main;
    private String home;

    public ZhorAppModel(String main, String home) {
        this.main = main;
        this.home = home;
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
}
