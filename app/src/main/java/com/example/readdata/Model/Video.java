package com.example.readdata.Model;

import java.io.Serializable;

public class Video implements Serializable {

    private String urlimage;
    private String name;
    private String title;
    private String urlmedia;
    private String luotxem;

    public Video(String urlimage, String name, String title, String urlmedia, String luotxem){
        this.urlimage = urlimage;
        this.name = name;
        this.title = title;
        this.urlmedia = urlmedia;
        this.luotxem = luotxem;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public String getUrlmedia() {
        return urlmedia;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getLuotxem(){
        return luotxem;
    }

}
