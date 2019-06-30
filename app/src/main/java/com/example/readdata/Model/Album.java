package com.example.readdata.Model;

import java.io.Serializable;

public class Album implements Serializable {

    private String urlimage;
    private String name;
    private String title;

    public Album(String urlimage, String name, String title){
        this.urlimage = urlimage;
        this.name = name;
        this.title = title;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

}
