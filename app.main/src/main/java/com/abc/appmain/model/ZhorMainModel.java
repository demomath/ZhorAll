package com.abc.appmain.model;



/**
 * Created by wudi on 2018/4/27.
 */

public class ZhorMainModel {
    private String name; //姓名
    private int age; //年龄

    public ZhorMainModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
