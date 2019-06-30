package com.example.readdata.Model;

import java.io.Serializable;

public class Artist implements Serializable {

    private String urlimage;
    private String name;

    public Artist(String urlimage, String name){
        this.urlimage = urlimage;
        this.name = name;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getName() {
        return name;
    }

}
