package com.example.longfootrefresh.bean;

import java.io.Serializable;

/**
 * Created by albus on 2017/11/9.
 */

public class StaticsBean implements Serializable{
    public StaticsBean(String name ,int id){
        this.name = name;
        this.id = id;
    }
    public String name;
    public int id;
}
